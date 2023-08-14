package com.codecool.trv.validation;

import jdk.jfr.ContentType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class ImageValidator {

    private static final List<String> IMAGE_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif", "image/bmp");

    public boolean isEmpty(MultipartFile file) {
        return file == null || file.isEmpty();
    }

    public boolean isImage(MultipartFile file) {
        System.out.println(file.getContentType());
        return IMAGE_TYPES.contains(file.getContentType());
    }

}
