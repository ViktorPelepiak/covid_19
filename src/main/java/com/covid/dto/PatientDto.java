package com.covid.dto;

import com.covid.entity.enums.Status;

import java.time.LocalDate;

public class PatientDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Status status;

    public PatientDto(){
        firstName = null;
        lastName = null;
        dateOfBirth = null;
        status = null;
    }

    public PatientDto(String firstName, String lastName, LocalDate dateOfBirth, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public PatientDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PatientDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public PatientDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public PatientDto setStatus(Status status) {
        this.status = status;
        return this;
    }
}
