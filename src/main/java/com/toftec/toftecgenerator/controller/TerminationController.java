package com.toftec.toftecgenerator.controller;

import com.toftec.toftecgenerator.model.Termination;
import com.toftec.toftecgenerator.service.TerminationPdfService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TerminationController {

    private final TerminationPdfService terminationPdfService;

    public TerminationController(TerminationPdfService terminationPdfService) {
        this.terminationPdfService = terminationPdfService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateTermination(@RequestBody Termination termination) {

        System.out.println(termination.toString());

        try {
            terminationPdfService.createTermination(termination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileSystemResource resource = new FileSystemResource("c:/orion/Wypowiedzenie" + termination.getFirstName() + termination.getLastName() + ".pdf");
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
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}

