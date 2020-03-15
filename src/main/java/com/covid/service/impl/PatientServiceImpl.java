package com.covid.service.impl;

import com.covid.entity.Patient;
import com.covid.repository.PatientRepository;
import com.covid.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(){}

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> findFirstById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient save(Patient entity) {
        return patientRepository.save(entity);
    }

    @Override
    public Patient update(Patient object) {
        return patientRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
