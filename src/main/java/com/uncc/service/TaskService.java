package com.uncc.service;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:12
 * @Description:
 */
public interface TaskService {
    /**
     * batch process the data of nba player in the csv file and import them into mysql
     */
    void processCSV() throws Exception;
}
