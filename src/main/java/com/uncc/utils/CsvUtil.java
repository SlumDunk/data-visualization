package com.uncc.utils;

import com.csvreader.CsvReader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 16:54
 * @Description:
 */
public class CsvUtil {
    /**
     * read data from a csv file
     *
     * @param path
     * @return
     */
    public static List<String[]> readCsv(String path) throws FileNotFoundException {
        File inputFile = ResourceUtils.getFile(path);
        InputStream inputStream = new FileInputStream(inputFile);
        CsvReader reader = new CsvReader(inputStream, ',', Charset.forName("UTF-8"));
        List<String[]> resultList = new ArrayList<String[]>();
        try {
            if (reader != null) {
                reader.readHeaders();
                while (reader.readRecord()) {
                    resultList.add(reader.getValues());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return resultList;
    }

    /**
     * write data to a csv file
     *
     * @param outputPath
     * @param data
     */
    public static void writeCsv(String outputPath, List<List<String>> data) {

    }
}
