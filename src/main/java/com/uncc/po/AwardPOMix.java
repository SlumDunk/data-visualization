package com.uncc.po;

import lombok.*;

/**
 * entity combines information for mapping several tables
 *
 * @Author: zerongliu
 * @Date: 11/11/18 15:58
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AwardPOMix {
    /**
     * season
     */
    private String season;
    /**
     * awards count
     */
    private Integer count;
    /**
     * west or east
     */
    private String conference;
    /**
     * name of team
     */
    private String teamName;
    /**
     * name of player
     */
    private String playerName;
}
