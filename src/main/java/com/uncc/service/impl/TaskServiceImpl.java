package com.uncc.service.impl;

import com.uncc.service.TaskService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:16
 * @Description:
 */
public class TaskServiceImpl implements TaskService {


    @Override
    public void processCSV() throws Exception {
    }
}
