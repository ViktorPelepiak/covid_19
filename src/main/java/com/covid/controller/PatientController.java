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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequestMapping("/patient")
@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private String preparePatientList(Map<String, Object> data) {
        Iterable<Patient> patients = patientRepository.findAll();
        Iterator<Patient> iterator = patients.iterator();

        List<Patient> ILess20I = new LinkedList<>();
        List<Patient> I20_40I = new LinkedList<>();
        List<Patient> I40_60I = new LinkedList<>();
        List<Patient> I60_80I = new LinkedList<>();
        List<Patient> IMore80I = new LinkedList<>();
        while (iterator.hasNext()) {
            Patient current = iterator.next();
            int age = current.calculateAge();
            if (age <= 20) {
                ILess20I.add(current);
            } else if (age <= 40) {
                I20_40I.add(current);
            } else if (age <= 60) {
                I40_60I.add(current);
            } else if (age <= 80) {
                I60_80I.add(current);
            } else {
                IMore80I.add(current);
            }
        }

        if (ILess20I.isEmpty()){
            data.put("ILess20I", "-");
        }else{
            Double lethal = (double) ILess20I.stream().filter(patient -> Status.LETHAL.equals(patient.getStatus())).count();
            data.put("ILess20I", lethal/ILess20I.size());
        }
        if (I20_40I.isEmpty()){
            data.put("I20_40I", "-");
        }else{
            Double lethal = (double) I20_40I.stream().filter(patient -> Status.LETHAL.equals(patient.getStatus())).count();
            data.put("I20_40I", lethal/I20_40I.size());
        }
        if (I40_60I.isEmpty()){
            data.put("I40_60I", "-");
        }else{
            Double lethal = (double) I40_60I.stream().filter(patient -> Status.LETHAL.equals(patient.getStatus())).count();
            data.put("I40_60I", lethal/I40_60I.size());
        }
        if (I60_80I.isEmpty()){
            data.put("I60_80I", "-");
        }else{
            Double lethal = (double) I60_80I.stream().filter(patient -> Status.LETHAL.equals(patient.getStatus())).count();
            data.put("I60_80I", lethal/I60_80I.size());
        }
        if (IMore80I.isEmpty()){
            data.put("IMore80I", "-");
        }else{
            Double lethal = (double) IMore80I.stream().filter(patient -> Status.LETHAL.equals(patient.getStatus())).count();
            data.put("IMore80I", lethal/IMore80I.size());
        }

        data.put("patients", patients);
        return "list";
    }

    @GetMapping
    public String getAllPatients(Map<String, Object> data) {
        return preparePatientList(data);
    }

    @GetMapping("/add")
    public String pageForAdd(Map<String, Object> data) {
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
        return preparePatientList(data);
    }

    @GetMapping("/update")
    public String getUpdatePage(@RequestParam String idForUpdate, Map<String, Object> data) {
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
        return preparePatientList(data);
    }

    @PostMapping("/delete")
    public String deletePatient(@RequestParam String idForDelete, Map<String, Object> data) {
        patientRepository.deleteById(Long.valueOf(idForDelete));
        return preparePatientList(data);
    }
}
