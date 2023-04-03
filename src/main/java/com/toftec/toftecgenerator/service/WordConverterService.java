package com.toftec.toftecgenerator.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@Service
public class WordConverterService {

   public String deletePostalCode(String cityWithPostalCode) {

        return cityWithPostalCode.replaceAll("[0-9-,]", "");
    }

    public String convertDate(String date) throws ParseException {
        date = date.substring(0, 10);
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateResult = originalFormat.parse(date);
        return targetFormat.format(dateResult);
    }
}
