package com.uncc.common;

import java.util.*;

/**
 * @Author: zerongliu
 * @Date: 10/31/18 10:48
 * @Description:
 */
public interface Constants {

    public static final Map<String, String> positionMap = new HashMap<String, String>() {{
        put("PG", "point guard");
        put("SG", "shooting guard");
        put("F", "forward");
        put("C", "center");
        put("SF", "small forward");
        put("PF", "power forward");
        put("G", "guard");
        put("FC", "forward center");
        put("GF", "guard forward");
        put("F-C", "forward center");
        put("G-F", "guard forward");

    }};

}
