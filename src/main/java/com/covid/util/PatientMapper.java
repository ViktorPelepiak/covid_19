package com.covid.util;

import com.covid.dto.PatientDto;
import com.covid.entity.Patient;
import com.covid.entity.Person;

public class PatientMapper {
    public static PatientDto patientToDto(Patient patient) {
        return new PatientDto()
                .setFirstName(patient.getPerson().getFirstName())
                .setLastName(patient.getPerson().getLastName())
                .setDateOfBirth(patient.getPerson().getDateOfBirth())
                .setStatus(patient.getStatus());
    }

    public static Patient patientFromDto(PatientDto patientDto) {
        return new Patient()
                .setPerson(new Person()
                        .setFirstName(patientDto.getFirstName())
                        .setLastName(patientDto.getLastName())
                        .setDateOfBirth(patientDto.getDateOfBirth()))
                .setStatus(patientDto.getStatus());
    }
}
