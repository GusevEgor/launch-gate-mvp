package com.example.core.minio;

import com.example.core.exception.FileException;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService {

    @Value("${minio.internal-url}")
    private String url;

    @Value("${minio.bucket.media-bucket}")
    private String mediaBucketName;

    @Value("${minio.bucket.json-bucket}")
    private String jsonBucketName;

    private final MinioClient minioClient;

    /**
     * Метод для сохранения файла в MinIO.
     *
     * @param file       {@link MultipartFile}
     * @param objectName {@link String}
     * @return {@link String}
     */
    public String saveFileToMinio(MultipartFile file, String objectName) {
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(mediaBucketName)
                            .object(objectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            log.info("File {} uploaded to MinIO", objectName);
        } catch (Exception e) {
            log.error("Failed to upload file to MinIO: {}", e.getMessage());
            throw new FileException("Failed to upload file");
        }

        return getFileUrl(objectName, mediaBucketName);
    }

    /**
     * Сохраняет строковое содержимое (JSON) в MinIO.
     * @param jsonString {@link String} Строка JSON.
     * @param objectName {@link String} Уникальное имя объекта (ключа) в MinIO.
     * @return Путь к сохраненному объекту.
     */
    public String saveJsonFileFromString(String jsonString, String objectName) {

        byte[] jsonBytes = jsonString.getBytes(StandardCharsets.UTF_8);
        try (InputStream inputStream = new ByteArrayInputStream(jsonBytes)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(jsonBucketName)
                            .object(objectName)
                            .stream(inputStream, jsonBytes.length, -1)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .build());
            log.info("Json converted to file {} uploaded to MinIO", objectName);
        } catch (Exception e) {
            log.error("Failed to upload Json converted string to file to MinIO: {}", e.getMessage());
            throw new FileException("Failed to upload file");
        }

        return objectName;
    }

    /**
     * Получает файл в виде потока {@link InputStream}.
     *
     * @param objectName {@link String}
     * @return {@link InputStream}
     */
    public InputStream getFileStream(String objectName, String bucket) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            log.error("Failed to get file from MinIO with object name {}: {}", objectName, e.getMessage());
            throw new FileException("Failed to get file");
        }
    }

    /**
     * Метод для удаления файла из MinIO.
     *
     * @param objectName {@link String}
     */
    public void deleteFile(String objectName, String bucket) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build());

            log.info("File {} deleted from MinIO", objectName);
        } catch (Exception e) {
            log.error("Failed to delete file from MinIO with object name {}: {}", objectName, e.getMessage());
            throw new FileException("Failed to delete file");
        }
    }

    /**
     * Метод для получения статистики о файле.
     *
     * @param objectName {@link String}
     * @return {@link StatObjectResponse}
     */
    public StatObjectResponse getFileStats(String objectName) {
        try {
            return minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(mediaBucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            log.error("Failed to get file stats from MinIO with object name {}: {}", objectName, e.getMessage());
            throw new FileException("Failed to get file stats");
        }
    }


    /**
     * Метод для генерации URL для получения файла по постоянной ссылке.
     *
     * @param objectKey {@link String}
     * @return {@link String}
     */
    private String getFileUrl(String objectKey, String bucket) {
        return String.format("%s/%s/%s", url, bucket, objectKey);
    }
}
