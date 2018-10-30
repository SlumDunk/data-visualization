package com.uncc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zerongliu
 * @Date: 10/29/18 22:16
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryVO {
    private String command;
}
