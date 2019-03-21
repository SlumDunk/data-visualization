package com.uncc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Year;

/**
 * player entity for transfering between service layer and controller layer
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PlayerDTO {
    /**
     * primary key
     */
    private Integer playerId;
    /**
     * name of player
     */
    private String playerName;
    /**
     * the year they enter nba
     */
    private Year playerDraftYear;
    /**
     * position of player
     */
    private String playerPosition;
    /**
     * height
     */
    private Double playerHeight;
    /**
     * weight
     */
    private Double playerWeight;
    /**
     * bmi data
     */
    private Double bmi;


}