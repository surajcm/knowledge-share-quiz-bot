package com.knowledge.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class KnowledgeShareQuizBotApplication {

    public static void main(final String[] args) {
        SpringApplication.run(KnowledgeShareQuizBotApplication.class, args);
    }

}
