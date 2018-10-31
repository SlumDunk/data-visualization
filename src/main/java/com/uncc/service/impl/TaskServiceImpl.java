package com.uncc.service.impl;

import com.uncc.service.TaskService;
import com.uncc.utils.CsvUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:16
 * @Description:
 */
@Component
public class TaskServiceImpl implements TaskService {


    @Override
    public void processCSV(String fileName) throws Exception {

        List<String[]> dataList = CsvUtil.readCsv("classpath:" + fileName);

    }
}
