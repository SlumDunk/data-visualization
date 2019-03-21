package com.uncc.mapper;

import com.uncc.po.TeamPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * operates team table
 *
 * @Author: zerongliu
 * @Date: 10/30/18 15:27
 * @Description:
 */
@Component
public interface TeamMapper {
    /**
     * insert team records into team table
     *
     * @param team
     * @return
     */
    @Insert("insert into team(team_name,team_conference) values(#{teamName},#{teamConference})")
    @Options(useGeneratedKeys = true, keyProperty = "teamId", keyColumn = "team_id")
    public Integer insertTeam(TeamPO team);

    /**
     * query team list
     *
     * @return
     */
    @Results({
            @Result(column = "team_id", property = "teamId"),
            @Result(column = "team_name", property = "teamName"),
            @Result(column = "team_conference", property = "teamConference")
    })
    @Select("select * from team")
    public List<TeamPO> queryTeamList();
}
