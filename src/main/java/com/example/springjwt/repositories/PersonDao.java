package com.example.springjwt.repositories;

import com.example.springjwt.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person,Integer> {
    public Person getByUsername(String username);
}
