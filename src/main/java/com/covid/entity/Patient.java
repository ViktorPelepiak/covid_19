package com.covid.entity;

import com.covid.dto.PatientDto;
import com.covid.entity.enums.Status;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Patient(){
        id = null;
        person = new Person();
        status = null;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Patient setPerson(Person person) {
        this.person = person;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Patient setStatus(Status status) {
        this.status = status;
        return this;
    }
}
