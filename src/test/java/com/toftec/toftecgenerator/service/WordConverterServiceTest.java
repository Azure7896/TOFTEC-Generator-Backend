package com.toftec.toftecgenerator.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class WordConverterServiceTest {
    @Autowired
    private WordConverterService wordConverterService;

    @Test
    void deletePostalCode() {
        String emptyPostalCode = wordConverterService.deletePostalCode("40-085");
        System.out.println(emptyPostalCode);
        assertTrue(emptyPostalCode.isEmpty());
    }

    @Test
    void convertDate() {
        String date;
        try {
            date = wordConverterService.convertDate("2023-04-18T22:00:00.000Z");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertEquals(date, "18.04.2023");
    }
}