package com.example.spring_s3_demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    public AmazoneS3 s3(){

        AWSCredentials awsCredentials=new BasicAWSCredentials(accessKey,secret);

    }

}
