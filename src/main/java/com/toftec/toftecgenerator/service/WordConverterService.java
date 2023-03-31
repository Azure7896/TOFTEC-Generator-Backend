package com.toftec.toftecgenerator.service;

import org.springframework.stereotype.Service;


@Service
public class WordConverterService {

    public String convertFirstName(String firstName) {
        String firstNameLastChar = (firstName.substring(firstName.length() - 1));
        if (firstNameLastChar.equals("a")) {
            firstName = firstName.substring(0, firstName.length() - 1) + "ą";
        }
        return firstName;
    }

    public String deletePostalCode(String cityWithPostalCode) {
        return cityWithPostalCode.replaceAll("[0-9-,]", "");
    }

}
