package com.example.springjwt.services;

import com.example.springjwt.entities.Person;
import com.example.springjwt.repositories.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDao personDao;

    @Override
    public Person getByUsername(String username) {
        return personDao.getByUsername(username);
    }
}
