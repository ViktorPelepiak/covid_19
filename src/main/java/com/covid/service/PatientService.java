package com.covid.service;

import com.covid.entity.Patient;
import com.covid.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<Patient> findFirstById(Long id);

    List<Patient> findAll();

    Patient save(Patient entity);

    Patient update(Patient object);

    void deleteById(Long id);
}
