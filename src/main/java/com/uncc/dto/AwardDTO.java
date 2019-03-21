package com.uncc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * award entity for transfering between service layer and controller layer
 *
 * @Author: zerongliu
 * @Date: 11/11/18 16:08
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AwardDTO {
    /**
     * season
     */
    private String season;
    /**
     * number of awards
     */
    private Integer count;
    /**
     * east or west
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
