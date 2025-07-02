package com.app.app.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class BucketService {

    private AmazonS3 amazonS3;
    public BucketService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadFile(MultipartFile file, String bucketName, long carId) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload an empty file.");
        }

        String fileName = carId + "_" + file.getOriginalFilename();  // Unique file name

        try (InputStream inputStream = file.getInputStream()) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, fileName, inputStream, metadata);
            return amazonS3.getUrl(bucketName, fileName).toString();// Return S3 file URL
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }
}
