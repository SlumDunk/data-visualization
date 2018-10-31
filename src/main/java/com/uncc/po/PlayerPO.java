package com.uncc.po;

import lombok.*;

import java.time.Year;

/**
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

    private String playerName;

    private Year playerDraftYear;

    private String playerPosition;

    private Double playerHeight;

    private Double playerWeight;

    private Double bmi;


}