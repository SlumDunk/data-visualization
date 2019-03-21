package com.uncc.service;

import org.springframework.stereotype.Service;

/**
 * service for parsing csv file and persist data into db
 *
 * @Author: zerongliu
 * @Date: 10/30/18 16:12
 * @Description:
 */
@Service
public interface TaskService {
    /**
     * process the data of nba player in the csv file and import them into mysql
     *
     * @param fileName
     * @throws Exception
     */
    void processCSV(String fileName) throws Exception;
}
