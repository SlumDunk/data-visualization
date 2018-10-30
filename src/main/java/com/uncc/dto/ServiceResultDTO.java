package com.uncc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 10/29/18 21:42
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResultDTO {
    /**
     * headers of columns
     */
    private List<String> headers;
    /**
     * result of data
     */
    private Object data;
}
