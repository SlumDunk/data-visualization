package com.uncc.mapper;

import com.uncc.po.PlayerPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 15:31
 * @Description:
 */
public interface PlayerMapper {

    @Insert("insert into player(player_name,player_draft_year,player_position,player_height,player_weight,team_id) values(#{playerName},#{playerDraftYear},#{playerPosition},#{playerHeight},#{playerWeight},#{teamId})")
    @Options(useGeneratedKeys = true, keyProperty = "player.playerId")
    public Integer insertPlayer(PlayerPO player);
}
