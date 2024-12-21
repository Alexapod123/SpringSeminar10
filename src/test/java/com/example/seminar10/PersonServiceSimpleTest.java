package com.example.seminar10;

import com.example.seminar10.model.Gender;
import com.example.seminar10.model.Person;
import com.example.seminar10.repository.PersonRepository;
import com.example.seminar10.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServiceSimpleTest {

    @Mock
    public PersonRepository repository;
    @InjectMocks
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
