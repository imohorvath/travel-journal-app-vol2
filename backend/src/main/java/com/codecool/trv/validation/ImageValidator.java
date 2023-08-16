package com.codecool.trv.validation;

import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@Component
public class ImageValidator {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "bmp");

    /**
     * Checks if the file is empty.
     *
     * @param file The MultipartFile to check.
     * @return true if the file is empty or null, false otherwise.
     */
    public boolean isEmpty(MultipartFile file) {
        return file == null || file.isEmpty();
    }

    public boolean isValidImage(MultipartFile file) {
        return hasValidFormatByGuessing(file) && hasValidExtension(file);
    }

    /**
     * Validates if the provided file is a genuine image.
     *
     * @param file The file to validate.
     * @return true if the file is a valid image, false otherwise.
     */
    private boolean hasValidFormatByGuessing(MultipartFile file) {
        try {
            ImageFormat format = Imaging.guessFormat(file.getBytes());
            return format != ImageFormats.UNKNOWN;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates if the provided file's extension is one of the allowed extensions.
     *
     * @param file The file to validate.
     * @return true if the file's extension is allowed, false otherwise.
     */
    private boolean hasValidExtension(MultipartFile file) {
        String extension = getFileExtension(file.getOriginalFilename());
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

}
