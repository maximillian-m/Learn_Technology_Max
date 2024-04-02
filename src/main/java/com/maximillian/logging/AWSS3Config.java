package com.maximillian.logging;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class AWSS3Config {
    @Value("${application.aws.accessKey}")
    private String awsAccessKey;
    @Value("${application.aws.secretKey}")
    private String awsSecretKey;
    @Value("${application.aws.url}")
    private String endpointUrl;


    @Bean
    public S3Client getClient(){
        AwsCredentials credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);
        S3Client client = S3Client.builder()
                .endpointOverride(URI.create(endpointUrl).resolve(endpointUrl))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of("nyc3"))
                .build();
        return client;
    }

//@Bean
//public S3Client s3Client() {
//    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);
//
//    return S3Client.builder()
//            .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//            .endpointOverride(URI.create(endpointUrl))
//            .region(Region.AWS_GLOBAL)
//            .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
//            .build();
//}
}
