package com.maximillian.logging;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AwsS3BucketService {
    private final S3Client s3;
    @Value("${application.aws.s3.bucketName}")
    private String awsS3BucketName;

    public void uploadObject() {
        String bucketName = "example-space"; // Your Space name
        String objectKey = "folder-path/hello-world.txt"; // The object's key
        String content = "Hello, World!"; // The object's content

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .acl("private") // Access control list
                .build();

        s3.putObject(putObjectRequest, RequestBody.fromString(content));
        System.out.println("Successfully uploaded object: " + bucketName + "/" + objectKey);
    }

//    public void uploadObject(String key, byte[] file){
//        PutObjectRequest objectRequest = PutObjectRequest.builder()
//                .bucket(awsS3BucketName)
//                .key(key)
//                .build();
//        s3.putObject(objectRequest, RequestBody.fromBytes(file));
//    }

    public byte[] getObject(String key){
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(awsS3BucketName)
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> response = s3.getObject(request);
            try {
                return response.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException("Retrieving of object failed");
            }
        }
}
