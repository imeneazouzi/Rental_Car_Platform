package com.RentalCar.location.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/cars";

    public String saveImage(Long carId, MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }


            String fileName = "car-" + carId + "-" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);


            Files.copy(file.getInputStream(), filePath);

            return "/uploads/cars/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }
}

