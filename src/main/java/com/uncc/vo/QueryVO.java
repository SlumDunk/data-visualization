package com.uncc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * input parameter from clients map into this entity
 *
 * @Author: zerongliu
 * @Date: 10/29/18 22:16
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryVO {
    /**
     * name of file
     */
    private String fileName;
}
