package com.covid.service.impl;

import com.covid.entity.Person;
import com.covid.repository.PersonRepository;
import com.covid.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id){
        return personRepository.findById(id);
    }

    public Person save(Person entity){
        return (Person) personRepository.save(entity);
    }

    @Override
    public Person update(Person object) {
        return personRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

}
