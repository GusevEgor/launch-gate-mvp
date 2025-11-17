package com.example.core.service;

import com.example.core.dto.file.FileData;
import com.example.core.dto.file.FileUrlResponse;
import com.example.core.entity.Media;
import com.example.core.exception.FileException;
import com.example.core.exception.NotFoundByIdException;
import com.example.core.minio.MinioService;
import com.example.core.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import static com.example.core.util.Urls.DELETE_FILE_URL;
import static com.example.core.util.Urls.GET_FILE_URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final MediaRepository mediaRepository;
    private final MinioService minioService;

    @Value("${minio.bucket.media-bucket}")
    private String mediaBucketName;

    @Value("${minio.bucket.json-bucket}")
    private String jsonBucketName;


    /**
     * Метод для сохранения файла.
     *
     * @param file {@link MultipartFile}
     * @return {@link FileUrlResponse}
     */
    public FileUrlResponse saveFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new FileException("Failed to store empty file");
        }

        String objectName = UUID.randomUUID() + getFileExtension(file.getOriginalFilename());

        String minioUrl = minioService.saveFileToMinio(file, objectName);

        Media media = new Media();
        media.setFileName(objectName);
        media.setMinioUrl(minioUrl);
        media.setTypeMedia(file.getContentType());

        Media savedMedia = mediaRepository.save(media);

        return getFileUrl(savedMedia.getId());
    }

    /**
     * Метод для получения файла.
     *
     * @param id {@link Long}
     * @return {@link FileData}
     */
    public FileData getFile(Long id) {

        Media media = mediaRepository.findById(id).orElseThrow(() -> new FileException("File not found"));
        InputStream resource = minioService.getFileStream(media.getFileName(), mediaBucketName);
        InputStreamResource inputStreamResource = new InputStreamResource(resource);
        String fileType = minioService.getFileStats(media.getFileName()).contentType();

        FileData fileData = new FileData();
        fileData.setResource(inputStreamResource);
        fileData.setContentType(fileType);

        return fileData;
    }

    /**
     * Метод для удаления файла.
     *
     * @param id {@link Long}
     */
    public void deleteFile(Long id) {

        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(Media.class, id));

        minioService.deleteFile(media.getFileName(), mediaBucketName);
        mediaRepository.delete(media);
    }

    /**
     * Метод для сохранения JSON строки.
     * @param jsonString {@link String}
     * @return {@link String} Имя файла в MinIO
     */
    public String saveJsonString(String jsonString){
        String objectName = UUID.randomUUID() + ".json";
        minioService.saveJsonFileFromString(jsonString, objectName);
        return objectName;
    }

    /**
     * Метод для получения JSON строки.
     * @param objectName {@link String}
     * @return {@link String}
     */
    public String getJsonString(String objectName){
        try {
            return StreamUtils.copyToString(
                    minioService.getFileStream(objectName, jsonBucketName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Failed to convert JSON file to String {}: {}", objectName, e.getMessage());
            throw new FileException("Failed to get file");
        }
    }

    /**
     * Метод для удаления JSON файла.
     * @param objectName {@link String}
     */
    public void deleteJsonFile(String objectName){
        minioService.deleteFile(objectName, jsonBucketName);
    }

    /**
     * Метод для получения расширения файла.
     *
     * @param fileName {@link String}
     * @return {@link String}
     */
    private String getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".") + 1))
                .orElseThrow(() -> new FileException("File extension not found"));
    }

    /**
     * Метод для получения URL для получения файла по ссылке.
     *
     * @param id {@link Long} id {@link Media}
     * @return {@link String}
     */
    private FileUrlResponse getFileUrl(Long id) {
        String getFileUrl = GET_FILE_URL + id;
        String deleteFileUrl = DELETE_FILE_URL + id;
        return new FileUrlResponse(getFileUrl, deleteFileUrl);
    }
}

