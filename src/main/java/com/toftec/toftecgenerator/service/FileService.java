package com.toftec.toftecgenerator.service;

import org.apache.catalina.webresources.FileResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

    @Async
    public void deleteFileAfterPdfGeneration(String filePath) throws InterruptedException {
        Thread.sleep(90000);
        File file = new File(filePath);
        file.delete();
    }
}
