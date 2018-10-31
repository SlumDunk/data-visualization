package com.uncc.controller;


import com.uncc.service.TaskService;
import com.uncc.vo.QueryVO;
import com.uncc.vo.ResponseResultVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zerongliu
 * @Date: 10/29/18 20:53
 * @Description:
 */
@RestController
@RequestMapping("/nba/")
public class BaseController {

    @Autowired
    TaskService taskService;

    @ResponseBody
    @RequestMapping(value = "csv", method = RequestMethod.POST)
    public ResponseResultVO processCsv(@RequestBody QueryVO queryVO) {
        ResponseResultVO result = new ResponseResultVO();
        if (StringUtils.isNotBlank(queryVO.getFileName())) {
            try {
                taskService.processCSV(queryVO.getFileName());
                result.setCode("200");
                result.setMsg("success");
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode("404");
                result.setMsg("fail");
            }
        }
        return result;
    }
}
