package com.toftec.toftecgenerator.service;

import com.toftec.toftecgenerator.model.Termination;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;


@SpringBootTest
class TerminationPdfServiceTest {

    @Autowired
    private TerminationPdfService terminationPdfService;

    String filePath;


    @Test
    @BeforeEach
    void createTermination()  {
        Termination termination = new Termination("Jan", "Kowalski", "Janem", "Kowalskim",
                "Jabłeczna 32", "41-707 Ruda Śląska", "Company Sp. Z.O.O", "Testowa 45", "40-085, Katowice",
                "2023-04-05", "2021-03-02", "threeMonths");;
        try {
            this.filePath = terminationPdfService.createTermination(termination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        FileSystemResource fileSystemResource = new FileSystemResource(this.filePath);
        assertTrue(fileSystemResource.exists());
    }


    @Test
    void deleteFileAfterGeneration() {
        File file = new File(filePath);
        file.delete();
        FileSystemResource fileSystemResource = new FileSystemResource(this.filePath);
        assertFalse(fileSystemResource.exists());
    }
}