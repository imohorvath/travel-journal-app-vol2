package com.codecool.trv.service;

import com.codecool.trv.model.Note;
import com.codecool.trv.model.NoteImage;
import com.codecool.trv.repository.NoteImageRepository;
import com.codecool.trv.validation.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class NoteImageService {

    private final static String IMAGE_FILE_SYSTEM_PATH = System.getenv("note_image_path");
    private final NoteImageRepository noteImageRepository;
    private final ImageValidator imageValidator;

    @Autowired
    public NoteImageService(NoteImageRepository noteImageRepository, ImageValidator imageValidator) {
        this.noteImageRepository = noteImageRepository;
        this.imageValidator = imageValidator;
    }

    public NoteImage save(MultipartFile file, Note savedNote) {

        if (imageValidator.isEmpty(file) || !imageValidator.isValidImage(file)) {
            throw new IllegalArgumentException("Cannot upload file.");
        }

        String fileName = String.format("%s\\%s", IMAGE_FILE_SYSTEM_PATH, savedNote.getId());
        //TODO: remove sout
        System.out.println("!!!!!!!!!!!! " + fileName);
        Path path = Paths.get(fileName);

        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            BufferedOutputStream outputStream =
                    new BufferedOutputStream(
                            new FileOutputStream(new File(String.valueOf(path), file.getOriginalFilename())));

            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        String imagePath = String.format("images/notes/%s/%s", savedNote.getId(), file.getOriginalFilename());
        //TODO: remove sout
        System.out.println("IMAGE PATH FOR FRONTEND " + imagePath);
        NoteImage noteImageToSave = NoteImage.builder().url(imagePath).note(savedNote).build();
        NoteImage savedImage = noteImageRepository.save(noteImageToSave);
        return savedImage;
    }

    public void deleteNoteImagesByNoteId(Long noteId) {
        noteImageRepository.deleteAllByNote_Id(noteId);
    }
}
