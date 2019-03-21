package com.uncc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: zerongliu
 * @Date: 10/31/18 14:57
 * @Description:
 */
public class CommonUtil {
    /**
     * parse height field in csv
     *
     * @param sourceHeight
     * @return
     */
    public static Double getHeight(String sourceHeight) {
        if (StringUtils.isNoneBlank(sourceHeight)) {
            if (sourceHeight.endsWith("cm")) {
                return Double.parseDouble(sourceHeight.substring(0, sourceHeight.length() - 2));
            } else {
                String[] arrHeight = sourceHeight.split("-");
                return (Double.parseDouble(arrHeight[0]) * 12 + Double.parseDouble(arrHeight[1])) * 2.54;
            }
        }
        return 0.0;
    }

    /**
     * parse wieght field in csv
     *
     * @param sourceWeight
     * @return
     */
    public static Double getWeight(String sourceWeight) {
        if (StringUtils.isNoneBlank(sourceWeight)) {
            if (sourceWeight.endsWith("kg")) {
                return Double.parseDouble(sourceWeight.substring(0, sourceWeight.length() - 2));
            } else {
                return Double.parseDouble(sourceWeight) * 0.453592;
            }
        }
        return 0.0;
    }
}
