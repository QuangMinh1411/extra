package com.heaven.storage.service;

import com.heaven.storage.entity.ImageData;
import com.heaven.storage.repository.StorageRepository;
import com.heaven.storage.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData save = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        if(save!=null){
            return "file upload successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImage = repository.findByName(fileName);
        byte[] images=ImageUtil.decompressImage(dbImage.get().getImageData());
        return images;
    }
}
