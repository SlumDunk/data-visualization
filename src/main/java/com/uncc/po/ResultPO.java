package com.uncc.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * result interface between mapper layer and service layer
 *
 * @Author: zerongliu
 * @Date: 10/29/18 21:42
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultPO {
    /**
     * headers
     */
    private List<String> headers;
    /**
     * data
     */
    private Object data;
}
