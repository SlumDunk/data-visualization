package com.uncc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:00
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardRecord {
    /**
     * age of player
     */
    private String age;
    /**
     * east or west
     */
    private String conference;
    /**
     * time to get award
     */
    private String date;
    /**
     * time enter nba
     */
    private String draftYear;
    /**
     * height
     */
    private String height;
    /**
     * name of player
     */
    private String playerName;
    /**
     * position of player
     */
    private String position;
    /**
     * season get award
     */
    private String season;
    /**
     * abbreviation of season
     */
    private String seasonShort;
    /**
     * length of working time
     */
    private String seasonAge;
    /**
     * team information
     */
    private String team;
    /**
     * weight
     */
    private String weight;
    /**
     * real value
     */
    private String realValue;

}
