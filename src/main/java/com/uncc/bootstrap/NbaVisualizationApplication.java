package com.uncc.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.uncc"})
@ServletComponentScan("com.uncc.*")
@EnableAsync
@EnableScheduling
public class NbaVisualizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbaVisualizationApplication.class, args);
    }
}
