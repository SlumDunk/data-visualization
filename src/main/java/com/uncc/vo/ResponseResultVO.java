package com.uncc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * interface between clients and server
 *
 * @Author: zerongliu
 * @Date: 10/29/18 20:56
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResultVO {
    /**
     * return code, 200 is success
     */
    private String code;
    /**
     * short msg, default is success
     */
    private String msg;
    /**
     * return data
     */
    private Object data;
}
