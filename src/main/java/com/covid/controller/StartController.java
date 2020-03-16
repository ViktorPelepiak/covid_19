package com.covid.controller;

import com.covid.dto.PatientDto;
import com.covid.entity.enums.Status;
import com.covid.repository.PatientRepository;
import com.covid.util.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Map;

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

    @GetMapping("/hello")
    public String hello() {
        PatientDto patient = new PatientDto()
                .setFirstName("testFN")
                .setLastName("testLN")
                .setDateOfBirth(LocalDate.now())
                .setStatus(Status.CURED);
        patientRepository.save(PatientMapper.patientFromDto(patient));

        return "Hello, World!";
    }
}
