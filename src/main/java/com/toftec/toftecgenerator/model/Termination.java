package com.toftec.toftecgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Termination {

    private String firstName;
    private String lastName;
    private String instrumentalCaseFirstName;
    private String instrumentalCaseLastName;
    private String address;
    private String cityWithPostalCode;
    private String companyName;
    private String companyAddress;
    private String companyCityWithPostalCode;
    private String terminationDocumentDate;
    private String employmentContractDate;
    private String terminationPeriod;

}
