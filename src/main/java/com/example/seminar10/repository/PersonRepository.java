package com.example.seminar10.repository;

import com.example.seminar10.model.Gender;
import com.example.seminar10.model.Married;
import com.example.seminar10.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * @param gender
     * @return список юзеров по половой принадлежности
     */
    List<Person> findByGender(Gender gender);

    /**
     *
     * @param married
     * @return список юзеров по семейному положению
     */
    List<Person> findByMarried(Married married);
}
