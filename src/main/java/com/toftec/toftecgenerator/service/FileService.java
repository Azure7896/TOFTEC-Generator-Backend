package com.toftec.toftecgenerator.service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

    @Async
    public void deleteFileAfterPdfGeneration(String filePath) throws InterruptedException {
        Thread.sleep(5000);
        File file = new File(filePath);
        file.delete();
    }
}
