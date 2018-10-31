package com.uncc.mapper;

import com.uncc.po.AwardPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 15:31
 * @Description:
 */
@Component
public interface AwardMapper {
    @Insert("insert into award(team_id, player_id,award_date,season_age,season) values(#{teamId},#{playerId},#{awardDate},#{seasonAge},#{season})")
    @Options(useGeneratedKeys = true, keyProperty = "awardId", keyColumn = "award_id")
    public Integer insertAward(AwardPO award);


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
}
