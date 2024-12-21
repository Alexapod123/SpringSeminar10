package com.example.seminar10.service;

import com.example.seminar10.model.Gender;
import com.example.seminar10.model.Married;
import com.example.seminar10.model.Person;
import com.example.seminar10.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Optional<Person> findById(Long id){
        return repository.findById(id);
    }

    public List<Person> findByGender(Gender gender){
        return repository.findByGender(gender);
    }

    public List<Person> findByMarried(Married married){
        return repository.findByMarried(married);
    }

    public Person save(Person person){
        return repository.save(person);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
