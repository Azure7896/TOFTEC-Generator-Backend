package com.toftec.toftecgenerator.controller;

import com.toftec.toftecgenerator.model.Termination;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerminationController {

    @PostMapping("/generate")
    public void generateTermination(@RequestBody Termination termination){
        System.out.println(termination.toString());
    }


}
