package com.uncc.mapper;

import com.uncc.po.PlayerPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 15:31
 * @Description:
 */
@Component
public interface PlayerMapper {

    @Insert("insert into player(player_name,player_draft_year,player_position,player_height,player_weight,bmi) values(#{playerName},#{playerDraftYear},#{playerPosition},#{playerHeight},#{playerWeight},#{bmi})")
    @Options(useGeneratedKeys = true, keyProperty = "playerId",keyColumn="player_id")
    public Integer insertPlayer(PlayerPO player);


    @Results({
            @Result(column = "player_id", property = "playerId"),
            @Result(column = "player_name", property = "playerName"),
            @Result(column = "player_draft_year", property = "playerDraftYear"),
            @Result(column = "player_position", property = "playerPosition"),
            @Result(column = "player_height", property = "playerHeight"),
            @Result(column = "player_weight", property = "playerWeight"),
            @Result(column = "bmi", property = "bmi")
    })
    @Select("select * from player")
    public List<PlayerPO> queryPlayerList();
}
