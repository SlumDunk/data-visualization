package com.uncc.mapper;

import com.uncc.po.AwardPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 15:31
 * @Description:
 */
public interface AwardMapper {
    @Insert("insert into award(player_id,award_date,season_age,season) values(#{playerId},#{awardDate},#{seasonAge},#{season})")
    @Options(useGeneratedKeys = true, keyProperty = "award.awardId")
    public Integer insertAward(AwardPO award);
}
