package com.uncc.mapper;

import com.uncc.po.AwardPO;
import com.uncc.po.AwardPOMix;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * operate award table
 *
 * @Author: zerongliu
 * @Date: 10/30/18 15:31
 * @Description:
 */
@Component
public interface AwardMapper {
    /**
     * insert award into db
     *
     * @param award
     * @return
     */
    @Insert("insert into award(team_id, player_id,award_date,season_age,season) values(#{teamId},#{playerId},#{awardDate},#{seasonAge},#{season})")
    @Options(useGeneratedKeys = true, keyProperty = "awardId", keyColumn = "award_id")
    public Integer insertAward(AwardPO award);

    /**
     * query all the award list
     *
     * @return
     */
    @Results({
            @Result(column = "player_id", property = "playerId"),
            @Result(column = "award_id", property = "awardId"),
            @Result(column = "team_id", property = "teamId"),
            @Result(column = "award_date", property = "awardDate"),
            @Result(column = "season_age", property = "seasonAge"),
            @Result(column = "season", property = "season"),
    })
    @Select("select * from award")
    public List<AwardPO> queryAwardList();

    /**
     * get award list of different confernces
     *
     * @return
     */
    @Results({
            @Result(column = "award_count", property = "count"),
            @Result(column = "team_conference", property = "conference"),
            @Result(column = "season", property = "season"),
    })
    @Select("select season,team_conference,count(*) as award_count from (select a.season,t.team_conference from award a join team t where a.team_id=t.team_id) as tmp group by team_conference,season order by season;")
    public List<AwardPOMix> queryConferenctAwardList();

    /**
     * get award information of each team
     *
     * @return
     */
    @Results({
            @Result(column = "award_count", property = "count"),
            @Result(column = "team_name", property = "teamName")
    })
    @Select("select count(*)as award_count, t.team_name from award a join team t on a.team_id=t.team_id group by t.team_name order by award_count desc;")
    public List<AwardPOMix> queryAwardList4Team();

    /**
     * get award information of the top 10 players
     *
     * @return
     */
    @Results({
            @Result(column = "award_count", property = "count"),
            @Result(column = "player_name", property = "playerName")
    })
    @Select("select count(*)as award_count, p.player_name from award a join player p on p.player_id=a.player_id group by p.player_id order by award_count desc limit 10;")
    public List<AwardPOMix> queryTop10Player();
}
