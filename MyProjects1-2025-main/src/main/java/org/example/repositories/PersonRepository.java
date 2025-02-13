package org.example.repositories;

import org.example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>{

     Person findByEmail(String email);
     List<Person> findAll();


}
