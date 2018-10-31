package com.uncc.service.impl;

import com.uncc.common.Constants;
import com.uncc.entity.AwardRecord;
import com.uncc.mapper.AwardMapper;
import com.uncc.mapper.PlayerMapper;
import com.uncc.mapper.TeamMapper;
import com.uncc.po.AwardPO;
import com.uncc.po.PlayerPO;
import com.uncc.po.TeamPO;
import com.uncc.service.TaskService;
import com.uncc.utils.CommonUtil;
import com.uncc.utils.CsvUtil;
import com.uncc.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.Year;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:16
 * @Description:
 */
@Component
public class TaskServiceImpl implements TaskService {
    /**
     * store the team information
     */
    Map<String, Set<String>> conferenceMap = new HashMap<String, Set<String>>();

    /**
     * the teamname and id map
     */
    Map<String, Integer> teamMap = new HashMap<String, Integer>();
    /**
     * the playerName and id map
     */
    Map<String, Integer> playerMap = new HashMap<String, Integer>();
    /**
     * the award and id map
     */
    Map<String, Integer> awardMap = new HashMap<String, Integer>();
    /**
     * store the records in the csv
     */
    List<AwardRecord> records = new ArrayList<AwardRecord>();
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    AwardMapper awardMapper;

    @Override
    @Transactional
    public void processCSV(String fileName) throws Exception {
        List<String[]> dataList = CsvUtil.readCsv("classpath:" + fileName);
        List<TeamPO> teamList = teamMapper.queryTeamList();
        if (!CollectionUtils.isEmpty(teamList)) {
            teamList.forEach(new Consumer<TeamPO>() {
                @Override
                public void accept(TeamPO teamPO) {
                    teamMap.put(teamPO.getTeamName(), teamPO.getTeamId());
                }
            });

        }
        List<PlayerPO> playerList = playerMapper.queryPlayerList();
        if (!CollectionUtils.isEmpty(playerList)) {
            playerList.forEach(new Consumer<PlayerPO>() {
                @Override
                public void accept(PlayerPO playerPO) {
                    playerMap.put(playerPO.getPlayerName() + playerPO.getPlayerDraftYear(), playerPO.getPlayerId());
                }
            });


        }
        List<AwardPO> awardList = awardMapper.queryAwardList();
        if (!CollectionUtils.isEmpty(awardList)) {
            awardList.forEach(new Consumer<AwardPO>() {
                @Override
                public void accept(AwardPO awardPO) {
                    awardMap.put(awardPO.getPlayerId() + DateUtil.parseDate2Str(DateUtil.DEFAULT_DATE_PATTERN, awardPO.getAwardDate()) + "", awardPO.getAwardId());
                }
            });


        }

        conferenceMap.put("East", new HashSet<String>());
        conferenceMap.get("East").add("Washington Bullets");
        if (!CollectionUtils.isEmpty(dataList)) {
            dataList.forEach(new Consumer<String[]>() {
                @Override
                public void accept(String[] columns) {
                    if (!ObjectUtils.isEmpty(columns)) {
                        AwardRecord awardRecord = new AwardRecord();
                        awardRecord.setAge(columns[0]);
                        awardRecord.setConference(columns[1]);
                        awardRecord.setDate(columns[2]);
                        awardRecord.setDraftYear(columns[3]);
                        awardRecord.setHeight(columns[4]);
                        awardRecord.setPlayerName(columns[5]);
                        awardRecord.setPosition(Constants.positionMap.get(columns[6]));
                        awardRecord.setSeason(columns[7]);
                        awardRecord.setSeasonShort(columns[8]);
                        awardRecord.setSeasonAge(columns[9]);
                        awardRecord.setTeam(columns[10]);
                        awardRecord.setWeight(columns[11]);
                        awardRecord.setRealValue(columns[12]);
                        records.add(awardRecord);

                        if (CollectionUtils.isEmpty(teamMap)) {
                            if (StringUtils.isNotBlank(columns[1]) && StringUtils.isNoneBlank(columns[columns.length - 3])) {
                                String conference = columns[1];
                                String teamName = columns[columns.length - 3];
                                if (conferenceMap.get(conference) != null) {
                                    conferenceMap.get(conference).add(teamName);
                                } else {
                                    Set<String> teamNameSet = new HashSet<String>();
                                    teamNameSet.add(teamName);
                                    conferenceMap.put(conference, teamNameSet);
                                }

                            }
                        }

                    }
                }
            });
            //import the team information into database
            if (CollectionUtils.isEmpty(teamMap) && !CollectionUtils.isEmpty(conferenceMap)) {
                conferenceMap.forEach(new BiConsumer<String, Set<String>>() {
                    @Override
                    public void accept(String key, Set<String> values) {
                        TeamPO team;
                        for (String teamName : values
                                ) {
                            if (!teamMap.containsKey(teamName)) {
                                team = new TeamPO();
                                team.setTeamConference(key);
                                team.setTeamName(teamName);
                                teamMapper.insertTeam(team);
                                teamMap.put(teamName, team.getTeamId());
                            }
                        }
                    }
                });
            }
            //import the data of players into database
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(new Consumer<AwardRecord>() {
                    @Override
                    public void accept(AwardRecord awardRecord) {
                        PlayerPO player;
                        if (!playerMap.containsKey(awardRecord.getPlayerName() + awardRecord.getDraftYear())) {
                            player = new PlayerPO();
                            player.setPlayerDraftYear(Year.parse(awardRecord.getDraftYear()));
                            player.setPlayerHeight(CommonUtil.getHeight(awardRecord.getHeight()));
                            player.setPlayerName(awardRecord.getPlayerName());
                            player.setPlayerPosition(awardRecord.getPosition());
                            player.setPlayerWeight(CommonUtil.getWeight(awardRecord.getWeight()));
                            player.setBmi(player.getPlayerWeight() / Math.pow(player.getPlayerHeight() / 100, 2));
                            playerMapper.insertPlayer(player);
                            playerMap.put(player.getPlayerName() + player.getPlayerDraftYear(), player.getPlayerId());
                        }

                        if (!awardMap.containsKey(playerMap.get(awardRecord.getPlayerName() + awardRecord.getDraftYear()) + awardRecord.getDate())) {
                            AwardPO award = new AwardPO();
                            award.setPlayerId(playerMap.get(awardRecord.getPlayerName() + awardRecord.getDraftYear()));
                            award.setAwardDate(DateUtil.parseStr2Date(DateUtil.DEFAULT_DATE_PATTERN, awardRecord.getDate()));
                            award.setSeasonAge(Integer.parseInt(awardRecord.getSeasonAge()));
                            award.setSeason(Integer.parseInt(awardRecord.getSeasonShort()));
                            award.setTeamId(teamMap.get(awardRecord.getTeam()));
                            awardMapper.insertAward(award);
                        }
                    }
                });
            }

        }

    }
}
