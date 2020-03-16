package com.covid.controller;

import com.covid.entity.Patient;
import com.covid.entity.Person;
import com.covid.entity.enums.Status;
import com.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Map;

@RequestMapping("/patients")
@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public String getAllPatients(Map<String, Object> data) {
        Iterable<Patient> patients = patientRepository.findAll();
        data.put("patients", patients);
        return "list";
    }

    @GetMapping("/add")
    public String pageForAdd(Map<String, Object> data){
        return "addPage";
    }

    @PostMapping("/add")
    public String addNewPatient(
            @RequestParam String fName,
            @RequestParam String lName,
            @RequestParam String dob,
            @RequestParam String status,
            Map<String, Object> data) {
        Patient patient = new Patient()
                .setPerson(new Person()
                        .setFirstName(fName)
                        .setLastName(lName)
                        .setDateOfBirth(LocalDate.parse(dob)))
                .setStatus(Status.valueOf(status));
        patientRepository.save(patient);
        Iterable<Patient> patients = patientRepository.findAll();
        data.put("patients", patients);
        return "list";
    }

    @GetMapping("/update")
    public String getUpdatePage(@RequestParam String idForUpdate, Map<String, Object> data){
        Patient patient = patientRepository.findById(Long.valueOf(idForUpdate)).orElseThrow(EntityNotFoundException::new);
        data.put("id", idForUpdate);
        data.put("patient", patient);
        data.put("isLethal", patient.getStatus().equals(Status.LETHAL));
        return "update";
    }

    @PostMapping("/update")
    public String updatePatient(
            @RequestParam String idForUpdate,
            @RequestParam String fName,
            @RequestParam String lName,
            @RequestParam String dob,
            @RequestParam String status,
            Map<String, Object> data) {
        Patient patient = patientRepository.findById(Long.valueOf(idForUpdate)).orElseThrow(EntityNotFoundException::new);
        patient.getPerson().setFirstName(fName);
        patient.getPerson().setLastName(lName);
        patient.getPerson().setDateOfBirth(LocalDate.parse(dob));
        patient.setStatus(Status.valueOf(status));
        patientRepository.save(patient);
        Iterable<Patient> patients = patientRepository.findAll();
        data.put("patients", patients);
        return "list";
    }

    @PostMapping("/delete")
    public String deletePatient(@RequestParam String idForDelete, Map<String, Object> data) {
        patientRepository.deleteById(Long.valueOf(idForDelete));
        Iterable<Patient> patients = patientRepository.findAll();
        data.put("patients", patients);
        return "list";
    }
}
