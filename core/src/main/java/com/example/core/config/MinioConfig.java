package com.example.core.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioConfig {

    @Value("${minio.internal-url}")
    private String minioInternalUrl;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket.media-bucket}")
    private String mediaBucketName;

    @Value("${minio.bucket.json-bucket}")
    private String jsonBucketName;

    /**
     * Создаем бин MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioInternalUrl)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * Метод запускается после создания бина minioClient.
     * Проверяет, существует ли корзина, и создает ее, если нет.
     */
    @Bean
    public CommandLineRunner initMinioBucketsRunner(MinioClient minioClient) {
        return args -> {
            try {
                checkBucketExists(minioClient, mediaBucketName);
                checkBucketExists(minioClient, jsonBucketName);

            } catch (Exception e) {
                log.error("Error initializing MinIO: {}", e.getMessage());
            }
        };
    }


    private void checkBucketExists(MinioClient minioClient, String bucketName) {
        try {
            Boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());

            if (found.equals(false)) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
                log.info("Bucket '{}' created.", bucketName);

                String policyJson = createPublicReadPolicy(bucketName);
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(policyJson)
                        .build());
                log.info("Set 'public read' policy for bucket '{}'.", bucketName);

            } else {
                log.info("Bucket '{}' already exists.", bucketName);
            }
        } catch (Exception e) {
            log.error("Error checking MinIO bucket {}: {}", bucketName, e.getMessage());
        }
    }

    /**
     * Вспомогательный метод для генерации JSON-политики
     */
    private String createPublicReadPolicy(String bucketName) {
        String policy = """
        {
          "Version": "2012-10-17",
          "Statement": [
            {
              "Effect": "Allow",
              "Principal": "*",
              "Action": ["s3:GetObject"],
              "Resource": ["arn:aws:s3:::%s/*"]
            }
          ]
        }
        """;
        return String.format(policy, bucketName);
    }
}
