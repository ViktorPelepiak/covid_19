package com.covid.service;

import com.covid.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> findById(Long id);

    List<Person> findAll();

    Person save(Person entity);

    Person update(Person object);

    void deleteById(Long id);
}
