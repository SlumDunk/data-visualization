package com.uncc.mapper;

import com.uncc.po.PlayerPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * operate player table
 *
 * @Author: zerongliu
 * @Date: 10/30/18 15:31
 * @Description:
 */
@Component
public interface PlayerMapper {

    /**
     * insert new player into table
     *
     * @param player
     * @return
     */
    @Insert("insert into player(player_name,player_draft_year,player_position,player_height,player_weight,bmi) values(#{playerName},#{playerDraftYear},#{playerPosition},#{playerHeight},#{playerWeight},#{bmi})")
    @Options(useGeneratedKeys = true, keyProperty = "playerId", keyColumn = "player_id")
    public Integer insertPlayer(PlayerPO player);

    /**
     * query all players from db
     *
     * @return
     */
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

    /**
     * query weight data of different positions
     *
     * @return
     */
    @Results({
            @Result(column = "player_position", property = "playerPosition"),
            @Result(column = "player_weight", property = "playerWeight")
    })
    @Select("select player_position,player_weight from player")
    List<PlayerPO> getWeight();

    /**
     * query height data of different positions
     *
     * @return
     */
    @Results({
            @Result(column = "player_position", property = "playerPosition"),
            @Result(column = "player_height", property = "playerHeight")
    })
    @Select("select player_position,player_height from player")
    List<PlayerPO> getHeight();

    /**
     * query bmi data of different positions
     *
     * @return
     */
    @Results({
            @Result(column = "player_position", property = "playerPosition"),
            @Result(column = "player_height", property = "playerHeight")
    })
    @Select("select player_position,bmi from player")
    List<PlayerPO> getBMI();

    /**
     * query the range of bmi data of different positions
     *
     * @param position
     * @return
     */
    @Results({
            @Result(column = "player_position", property = "playerPosition"),
            @Result(column = "player_draft_year", property = "playerDraftYear"),
            @Result(column = "bmi", property = "bmi")
    })
    @Select("select p.player_position, p.player_draft_year, AVG(p.bmi) as bmi from player p where p.player_position=#{position} group by p.player_draft_year, p.player_position order by player_position,player_draft_year asc;")
    List<PlayerPO> getBMIRange(String position);
}
