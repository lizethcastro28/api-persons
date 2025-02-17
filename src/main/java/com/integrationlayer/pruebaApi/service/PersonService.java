package com.integrationlayer.pruebaApi.service;

import com.integrationlayer.pruebaApi.dao.IPersonDAO;
import com.integrationlayer.pruebaApi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private IPersonDAO personDAO;
    public Person save(Person t) {
        return personDAO.save(t);
    }
    public Person update(Person t) {
        return personDAO.save(t);
    }
    public void delete(Person t) {
        personDAO.delete(t);
    }
    public Iterable<Person> list() {
        return personDAO.findAll();
    }
    public Optional<Person> findById(Integer id) {
        return personDAO.findById(id);
    }
}
