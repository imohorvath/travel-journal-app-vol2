package com.codecool.trv.controller;

import com.codecool.trv.service.NoteImageService;
import com.codecool.trv.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class ImageController {

    private NoteImageService noteImageService;

    @Autowired
    public ImageController(NoteImageService noteImageService) {
        this.noteImageService = noteImageService;
    }

    @GetMapping(value = "/images/notes/{noteId}/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Resource getFileViaByteArrayResource(@PathVariable Long noteId, @PathVariable String filename) throws IOException, URISyntaxException {
        Path path = noteImageService.getImagePath(noteId, filename);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }
}
