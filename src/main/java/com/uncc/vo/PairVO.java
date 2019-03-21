package com.uncc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zerongliu
 * @Date: 11/6/18 16:31
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PairVO {
    /**
     * key
     */
    private Object key;
    /**
     * value
     */
    private Object value;
    /**
     * color
     */
    private String color;
}
