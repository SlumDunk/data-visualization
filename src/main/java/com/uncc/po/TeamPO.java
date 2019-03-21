package com.uncc.po;

import java.util.Date;

import lombok.*;

/**
 * team entity for mapping with table awards
 *
 * @Author: zerongliu
 * @Date: 10/30/18 10:35
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamPO {
    /**
     * primary key
     */
    private Integer teamId;

    /**
     * name of team
     */
    private String teamName;

    /**
     * belong to which area EAST or WEST
     */
    private String teamConference;

}