package com.uncc.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:00
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardRecords {
    private Integer age;
    private String conference;
    private String date;
    private String draftYear;
    private String height;
    private String playerName;
    private String position;
    private String season;
    private String seasonShort;
    private String seasonAge;
    private String team;
    private String weight;
    private String realValue;

}
