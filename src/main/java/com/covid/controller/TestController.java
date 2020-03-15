package com.covid.controller;

import com.covid.dto.PatientDto;
import com.covid.entity.Patient;
import com.covid.entity.enums.Status;
import com.covid.service.PatientService;
import com.covid.util.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class TestController {

    private final PatientService patientService;

    @Autowired
    public TestController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("hello")
    public String hello() {
        PatientDto patient = new PatientDto()
                .setFirstName("testFN")
                .setLastName("testLN")
                .setDateOfBirth(LocalDate.now())
                .setStatus(Status.CURED);
        patientService.save(PatientMapper.patientFromDto(patient));

        return "Hello, World!";
    }
}
