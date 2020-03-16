package com.covid.controller;

import com.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    private final PatientRepository patientRepository;

    @Autowired
    public StartController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
