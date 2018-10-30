package com.uncc.controller;


import com.uncc.vo.QueryVO;
import com.uncc.vo.ResponseResultVO;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zerongliu
 * @Date: 10/29/18 20:53
 * @Description:
 */
@RestController
@RequestMapping("/nba/")
public class BaseController {


    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseResultVO executeSqlCommand(@RequestBody QueryVO queryVO) {
        ResponseResultVO result = new ResponseResultVO();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }
}
