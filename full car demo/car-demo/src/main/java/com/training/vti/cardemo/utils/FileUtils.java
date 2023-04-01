package com.training.vti.cardemo.utils;

import com.training.vti.cardemo.exception.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtils {
    public void validateFile( MultipartFile file) {
        // Check filename
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.isEmpty()) {
            throw new BadRequestException("File name not be blank");
        }

        // image.png -> png
        // avatar.jpg -> jpg
        // Check file extension (jpg, png, jpeg)
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new BadRequestException("file not supported");
        }

        // Check file size (<= 2MB)
        double fileSize =  (double) (file.getSize() / 1_048_576);
        if( fileSize > 2) {
            throw new BadRequestException("file size not over 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        return fileName.substring(lastIndexOf + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> extensions = new ArrayList<>(List.of("png", "jpg", "jpeg", "pdf"));
        return extensions.contains(fileExtension.toLowerCase());
    }
}
