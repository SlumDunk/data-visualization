package com.uncc.controller;


import com.uncc.common.Constants;
import com.uncc.dto.PlayerDTO;
import com.uncc.dto.ServiceResultDTO;
import com.uncc.service.PhysicService;
import com.uncc.service.TaskService;
import com.uncc.vo.PairVO;
import com.uncc.vo.QueryVO;
import com.uncc.vo.ResponseResultVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
    @Autowired
    PhysicService physicService;

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

    /**
     * return height data of players
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "height", method = RequestMethod.GET)
    public ResponseResultVO getHeight() {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getHeight();
        dto2vo(result, resultDTO);
        return result;
    }

    /**
     * return weight of players
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "weight", method = RequestMethod.GET)
    public ResponseResultVO getWeight() {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getWeight();
        dto2vo(result, resultDTO);
        return result;
    }

    /**
     * return the range of bmi data of players according to different position
     *
     * @param position
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bmirange/{position}", method = RequestMethod.GET)
    public ResponseResultVO getBMIRange(@PathVariable(name = "position", required = true) String position) {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getBMIRange(position);
        if (ObjectUtils.allNotNull(resultDTO.getData())) {
            Map<String, List<PlayerDTO>> resultMap = (Map<String, List<PlayerDTO>>) resultDTO.getData();
            List<PairVO> data = new ArrayList<>();
            resultMap.forEach(new BiConsumer<String, List<PlayerDTO>>() {
                @Override
                public void accept(String key, List<PlayerDTO> values) {
                    data.add(new PairVO(key, values, Constants.colors[RandomUtils.nextInt(0, Constants.colors.length)]));
                }
            });
            result.setData(data);
            result.setCode("200");
            result.setMsg("success");
        }
        return result;
    }

    /**
     * return the awards data of east and west range by time
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "eastwest", method = RequestMethod.GET)
    public ResponseResultVO getEastWest() {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getEastWest();
        result.setData(resultDTO.getData());
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    /**
     * return the awards of different teams
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "teamrange", method = RequestMethod.GET)
    public ResponseResultVO getTeamRange() {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getTeamRange();
        result.setData(resultDTO.getData());
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    /**
     * return the top 10 players who have most rewards
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "top10", method = RequestMethod.GET)
    public ResponseResultVO getTop10() {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getTop10();
        result.setData(resultDTO.getData());
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    /**
     * return bmi data of players
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "bmi", method = RequestMethod.GET)
    public ResponseResultVO getBMI() {
        ResponseResultVO result = new ResponseResultVO();
        ServiceResultDTO resultDTO = physicService.getBMI();
        dto2vo(result, resultDTO);
        return result;
    }

    /**
     * get sliders for homepage
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sliders", method = RequestMethod.GET)
    public ResponseResultVO getSliders() {
        ResponseResultVO result = new ResponseResultVO();
        List<String> sliderList = new ArrayList<String>();
        sliderList.add("https://images.performgroup.com/di/library/omnisport/2e/71/kobebryant_mvcdhobu8ict1muufiwdyj8p3.jpg?t=-2034108174w=800&h=600");
        sliderList.add("http://europebasketball.net/basketball_wallpaper/michael_jordan_dunk_wallpaper.jpg");
        sliderList.add("https://matadormessenger.com/wp-content/uploads/2018/07/lebron.jpg");
        result.setData(sliderList);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    /**
     * tranfer dto to vo
     *
     * @param result
     * @param resultDTO
     */
    private void dto2vo(ResponseResultVO result, ServiceResultDTO resultDTO) {
        if (ObjectUtils.allNotNull(resultDTO.getData())) {
            Map<String, List<Double>> resultMap = (Map<String, List<Double>>) resultDTO.getData();
            List<PairVO> data = new ArrayList<>();
            resultMap.forEach(new BiConsumer<String, List<Double>>() {
                @Override
                public void accept(String key, List<Double> values) {
                    data.add(new PairVO(key, values, Constants.colors[RandomUtils.nextInt(0, Constants.colors.length)]));
                }
            });
            result.setData(data);
            result.setCode("200");
            result.setMsg("success");
        }
    }
}
