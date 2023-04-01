package com.toftec.toftecgenerator.controller;

import com.toftec.toftecgenerator.model.Termination;
import com.toftec.toftecgenerator.service.FileService;
import com.toftec.toftecgenerator.service.TerminationPdfService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TerminationController {

    private final TerminationPdfService terminationPdfService;

    private final FileService fileService;

    public TerminationController(TerminationPdfService terminationPdfService, FileService fileService) {
        this.terminationPdfService = terminationPdfService;
        this.fileService = fileService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateTermination(@RequestBody Termination termination) throws InterruptedException {

        String filePath;

        try {
            filePath = terminationPdfService.createTermination(termination);
        } catch (IOException | ParseException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileSystemResource resource = new FileSystemResource(filePath);
        MediaType mediaType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        ContentDisposition disposition = ContentDisposition
                .inline()
                .filename(resource.getFilename())
                .build();
        headers.setContentDisposition(disposition);
        fileService.deleteFileAfterPdfGeneration(filePath);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}

