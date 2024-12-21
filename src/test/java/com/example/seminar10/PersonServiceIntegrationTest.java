package com.example.seminar10;

import com.example.seminar10.model.Gender;
import com.example.seminar10.model.Person;
import com.example.seminar10.repository.PersonRepository;
import com.example.seminar10.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class PersonServiceIntegrationTest {
    @MockitoBean
    public PersonRepository repository;

    @Autowired
    public PersonService service;

    @Test
    public void updatePersonTest(){
        Person vasya = new Person();
        vasya.setId(1L);
        vasya.setGender(Gender.MALE);

        given(repository.findById(vasya.getId())).willReturn(Optional.of(vasya));

        verify(repository).findById(1L);
        verify(repository).save(vasya);


    }
}
