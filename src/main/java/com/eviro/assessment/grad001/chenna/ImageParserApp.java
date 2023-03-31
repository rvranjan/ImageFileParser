package com.eviro.assessment.grad001.chenna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eviro.assessment.grad001.chenna")
public class ImageParserApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(ImageParserApp.class, args);
    }
}
