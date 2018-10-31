package com.uncc.po;

import lombok.*;

import java.util.Date;

/**
 * @author zerongliu
 * @description:AwardPO
 * @date: 上午10:35:25 2018年4月10日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AwardPO {
    /**
     * primary key
     */
    private Integer awardId;

    private Integer playerId;

    private Integer teamId;

    private Date awardDate;

    private Integer seasonAge;

    private Integer season;

}