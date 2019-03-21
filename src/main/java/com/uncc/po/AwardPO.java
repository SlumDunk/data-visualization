package com.uncc.po;

import lombok.*;

import java.util.Date;

/**
 * award entity for mapping with table awards
 *
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
    /**
     * id of player
     */
    private Integer playerId;
    /**
     * id of team
     */
    private Integer teamId;
    /**
     * date when got award
     */
    private Date awardDate;
    /**
     * season age
     */
    private Integer seasonAge;
    /**
     * season
     */
    private Integer season;

}