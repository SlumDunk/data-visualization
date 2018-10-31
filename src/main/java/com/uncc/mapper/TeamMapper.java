package com.uncc.mapper;

import com.uncc.po.TeamPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 15:27
 * @Description:
 */
@Component
public interface TeamMapper {

    @Insert("insert into team(team_name,team_conference) values(#{teamName},#{teamConference})")
    @Options(useGeneratedKeys = true, keyProperty = "teamId", keyColumn="team_id")
    public Integer insertTeam(TeamPO team);

    @Results({
            @Result(column = "team_id", property = "teamId"),
            @Result(column = "team_name", property = "teamName"),
            @Result(column = "team_conference", property = "teamConference")
    })
    @Select("select * from team")
    public List<TeamPO> queryTeamList();
}
