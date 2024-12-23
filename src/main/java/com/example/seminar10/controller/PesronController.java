package com.example.seminar10.controller;

import com.example.seminar10.model.Gender;
import com.example.seminar10.model.Married;
import com.example.seminar10.model.Person;
import com.example.seminar10.service.FileGateWay;
import com.example.seminar10.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PesronController {
    private final PersonService service;
    private  final FileGateWay fileGateWay;

    @GetMapping
    public List<Person> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK).getBody();
    }

    @GetMapping("/number/{id}")
    public Optional<Person> getUserById(@PathVariable("id") Long id) {
        Optional<Person> personId;
        try{
            personId = service.findById(id);
        } catch (RuntimeException e) {
            return Optional.ofNullable(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Person()).getBody());
        }
        return new ResponseEntity<>(personId, HttpStatus.OK).getBody();
    }

    @GetMapping("/sp/{married}")
    public List<Person> findByMarried(@PathVariable("married") Married married){
        return new ResponseEntity<>(service.findByMarried(married), HttpStatus.OK).getBody();
    }

    @GetMapping("/gen/{gender}")
    public List<Person> findByGender(@PathVariable("gender") Gender gender){
        return new ResponseEntity<>(service.findByGender(gender), HttpStatus.OK).getBody();
    }

    @PostMapping("/add")
    public Person addPerson(@RequestBody Person person){
        fileGateWay.writeToFile(person.getName() + ".txt", person.toString());
        return new ResponseEntity<>(service.save(person), HttpStatus.OK).getBody();
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable("id") Long id) {
        person.setId(id);
        return new ResponseEntity<>(service.save(person), HttpStatus.OK).getBody();
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        service.deleteById(id);
        ResponseEntity.ok().build();
    }




}
