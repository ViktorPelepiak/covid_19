package com.covid.controller;

import com.covid.dto.PatientDto;
import com.covid.entity.Patient;
import com.covid.service.PatientService;
import com.covid.util.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity addNewPatient(@RequestBody PatientDto patient){
        patientService.save(PatientMapper.patientFromDto(patient));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deletePatient(@RequestParam Long id){
        patientService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
