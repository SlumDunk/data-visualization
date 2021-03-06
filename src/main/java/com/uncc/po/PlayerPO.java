package com.uncc.po;

import lombok.*;

import java.time.Year;

/**
 * player entity for mapping with table awards
 *
 * @author zerongliu
 * @description:teamPO
 * @date: 上午10:35:25 2018年4月10日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerPO {
    /**
     * primary key
     */
    private Integer playerId;
    /**
     * name of player
     */
    private String playerName;
    /**
     * year when enter into nba
     */
    private Year playerDraftYear;
    /**
     * position of player
     */
    private String playerPosition;
    /**
     * height information
     */
    private Double playerHeight;

    /**
     * wieght information
     */
    private Double playerWeight;
    /**
     * bmi information
     */
    private Double bmi;


}