package com.uncc.mapper;

import com.uncc.po.TeamPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 15:27
 * @Description:
 */
public interface TeamMapper {

    @Insert("insert into team(team_name,team_conference) values(#{teamName},#{teamConference})")
    @Options(useGeneratedKeys = true, keyProperty = "team.teamId")
    public Integer insertTeam(TeamPO team);
}
