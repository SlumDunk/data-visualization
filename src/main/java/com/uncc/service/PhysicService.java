package com.uncc.service;

import com.uncc.dto.ServiceResultDTO;
import org.springframework.stereotype.Service;

/**
 * service for query data of players
 *
 * @Author: zerongliu
 * @Date: 11/6/18 16:12
 * @Description:
 */
@Service
public interface PhysicService {
    /**
     * return hegiht of players
     *
     * @return
     */
    ServiceResultDTO getHeight();

    /**
     * return weight of players
     *
     * @return
     */
    ServiceResultDTO getWeight();

    /**
     * reutrn bmi data of players
     *
     * @return
     */
    ServiceResultDTO getBMI();

    /**
     * get rang of bmi data according to position
     *
     * @param position
     * @return
     */
    ServiceResultDTO getBMIRange(String position);

    /**
     * return awards data of east and west, range by time
     *
     * @return
     */
    ServiceResultDTO getEastWest();

    /**
     * return awards data of different teams
     *
     * @return
     */
    ServiceResultDTO getTeamRange();

    /**
     * return the top 10 players who get most awards
     *
     * @return
     */
    ServiceResultDTO getTop10();
}
