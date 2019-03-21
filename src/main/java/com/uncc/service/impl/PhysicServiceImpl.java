package com.uncc.service.impl;

import com.uncc.common.Constants;
import com.uncc.dto.AwardDTO;
import com.uncc.dto.PlayerDTO;
import com.uncc.dto.ServiceResultDTO;
import com.uncc.mapper.AwardMapper;
import com.uncc.mapper.PlayerMapper;
import com.uncc.po.AwardPOMix;
import com.uncc.po.PlayerPO;
import com.uncc.service.PhysicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: zerongliu
 * @Date: 11/6/18 16:13
 * @Description:
 */
@Component
public class PhysicServiceImpl implements PhysicService {

    /**
     * player mapper
     */
    @Autowired
    PlayerMapper playerMapper;

    /**
     * award mapper
     */
    @Autowired
    AwardMapper awardMapper;

    @Override
    public ServiceResultDTO getHeight() {
        List<PlayerPO> playerPOList = playerMapper.getHeight();
        Map<String, List<Double>> resultMap = new HashMap<String, List<Double>>();
        if (!CollectionUtils.isEmpty(playerPOList)) {
            playerPOList.forEach(new Consumer<PlayerPO>() {
                @Override
                public void accept(PlayerPO playerPO) {
                    if (resultMap.get(playerPO.getPlayerPosition()) != null) {
                        resultMap.get(playerPO.getPlayerPosition()).add(playerPO.getPlayerHeight());
                    } else {
                        List<Double> heightList = new ArrayList<>();
                        heightList.add(playerPO.getPlayerHeight());
                        resultMap.put(playerPO.getPlayerPosition(), heightList);
                    }
                }
            });
        }
        ServiceResultDTO result = new ServiceResultDTO();
        result.setData(resultMap);
        return result;
    }

    @Override
    public ServiceResultDTO getWeight() {
        List<PlayerPO> playerPOList = playerMapper.getWeight();
        Map<String, List<Double>> resultMap = new HashMap<String, List<Double>>();
        if (!CollectionUtils.isEmpty(playerPOList)) {
            playerPOList.forEach(new Consumer<PlayerPO>() {
                @Override
                public void accept(PlayerPO playerPO) {
                    if (resultMap.get(playerPO.getPlayerPosition()) != null) {
                        resultMap.get(playerPO.getPlayerPosition()).add(playerPO.getPlayerWeight());
                    } else {
                        List<Double> weightList = new ArrayList<>();
                        weightList.add(playerPO.getPlayerWeight());
                        resultMap.put(playerPO.getPlayerPosition(), weightList);
                    }
                }
            });
        }
        ServiceResultDTO result = new ServiceResultDTO();
        result.setData(resultMap);
        return result;
    }

    @Override
    public ServiceResultDTO getBMI() {
        List<PlayerPO> playerPOList = playerMapper.getBMI();
        Map<String, List<Double>> resultMap = new HashMap<String, List<Double>>();
        if (!CollectionUtils.isEmpty(playerPOList)) {
            playerPOList.forEach(new Consumer<PlayerPO>() {
                @Override
                public void accept(PlayerPO playerPO) {
                    if (resultMap.get(playerPO.getPlayerPosition()) != null) {
                        resultMap.get(playerPO.getPlayerPosition()).add(playerPO.getBmi());
                    } else {
                        List<Double> bmiList = new ArrayList<>();
                        bmiList.add(playerPO.getBmi());
                        resultMap.put(playerPO.getPlayerPosition(), bmiList);
                    }
                }
            });
        }
        ServiceResultDTO result = new ServiceResultDTO();
        result.setData(resultMap);
        return result;
    }

    @Override
    public ServiceResultDTO getBMIRange(String position) {
        position = Constants.positionMap.get(position.toUpperCase());
        List<PlayerPO> playerPOList = playerMapper.getBMIRange(position);
        Map<String, List<PlayerDTO>> resultMap = new HashMap<String, List<PlayerDTO>>();
        if (!CollectionUtils.isEmpty(playerPOList)) {
            playerPOList.forEach(new Consumer<PlayerPO>() {
                @Override
                public void accept(PlayerPO playerPO) {
                    PlayerDTO playerDTO = new PlayerDTO();
                    BeanUtils.copyProperties(playerPO, playerDTO, "playerPosition");
                    if (resultMap.get(playerPO.getPlayerPosition()) != null) {

                        resultMap.get(playerPO.getPlayerPosition()).add(playerDTO);
                    } else {
                        List<PlayerDTO> positionBmiRangeList = new ArrayList<>();
                        positionBmiRangeList.add(playerDTO);
                        resultMap.put(playerPO.getPlayerPosition(), positionBmiRangeList);
                    }
                }
            });
        }
        ServiceResultDTO result = new ServiceResultDTO();
        result.setData(resultMap);
        return result;
    }

    @Override
    public ServiceResultDTO getEastWest() {
        List<AwardPOMix> awardList = awardMapper.queryConferenctAwardList();
        ServiceResultDTO result = new ServiceResultDTO();
        if (!CollectionUtils.isEmpty(awardList)) {
            Map<String, Map<String, Integer>> resultMap = new HashMap<String, Map<String, Integer>>();
            awardList.forEach(new Consumer<AwardPOMix>() {
                @Override
                public void accept(AwardPOMix awardPOMix) {
                    if (resultMap.get(awardPOMix.getSeason()) != null) {

                        resultMap.get(awardPOMix.getSeason()).put(awardPOMix.getConference(), awardPOMix.getCount());
                    } else {
                        Map<String, Integer> conferenceMap = new HashMap<>();
                        conferenceMap.put(awardPOMix.getConference(), awardPOMix.getCount());
                        resultMap.put(awardPOMix.getSeason(), conferenceMap);
                    }
                }
            });
            List<Map<String, Object>> dataList = new ArrayList<>();
            resultMap.forEach(new BiConsumer<String, Map<String, Integer>>() {
                @Override
                public void accept(String key, Map<String, Integer> valueMap) {
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("season", key);
                    valueMap.forEach(new BiConsumer<String, Integer>() {
                        @Override
                        public void accept(String key, Integer value) {
                            dataMap.put(key, value);
                        }
                    });
                    dataList.add(dataMap);
                }
            });
            result.setData(dataList);
        }

        return result;
    }

    @Override
    public ServiceResultDTO getTeamRange() {
        List<AwardPOMix> awardPOList = awardMapper.queryAwardList4Team();
        List<AwardDTO> resultList = new ArrayList<>();
        transfer(awardPOList, resultList);
        ServiceResultDTO result = new ServiceResultDTO();
        result.setData(resultList);
        return result;
    }

    @Override
    public ServiceResultDTO getTop10() {
        List<AwardPOMix> awardPOList = awardMapper.queryTop10Player();
        List<AwardDTO> resultList = new ArrayList<>();
        transfer(awardPOList, resultList);
        ServiceResultDTO result = new ServiceResultDTO();
        result.setData(resultList);
        return result;
    }

    /**
     * transfer award po mix to award dto
     *
     * @param awardPOList
     * @param resultList
     */
    private void transfer(List<AwardPOMix> awardPOList, List<AwardDTO> resultList) {
        if (!CollectionUtils.isEmpty(awardPOList)) {
            awardPOList.forEach(new Consumer<AwardPOMix>() {
                @Override
                public void accept(AwardPOMix awardPOMix) {
                    AwardDTO awardDTO = new AwardDTO();
                    BeanUtils.copyProperties(awardPOMix, awardDTO);
                    resultList.add(awardDTO);
                }
            });
        }
    }
}
