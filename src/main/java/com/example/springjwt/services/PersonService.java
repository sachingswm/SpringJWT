package com.example.springjwt.services;

import com.example.springjwt.entities.Person;

public interface PersonService {
    public Person getByUsername(String username);
}
