package com.uncc.service;

import org.springframework.stereotype.Service;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:12
 * @Description:
 */
@Service
public interface TaskService {
    /**
     * entity process the data of nba player in the csv file and import them into mysql
     */
    void processCSV(String fileName) throws Exception;
}
