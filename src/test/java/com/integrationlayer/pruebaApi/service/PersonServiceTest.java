package com.integrationlayer.pruebaApi.service;

import com.integrationlayer.pruebaApi.dao.IPersonDAO;
import com.integrationlayer.pruebaApi.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private IPersonDAO personDAO;

    @InjectMocks
    private PersonService personService;

    @Test
    void save() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");

        Mockito.when(personDAO.save(person)).thenReturn(person);

        Person savedPerson = personService.save(person);

        Mockito.verify(personDAO).save(person);
        assertEquals(person, savedPerson);
    }

    @Test
    void update() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");
        Mockito.when(personDAO.save(person)).thenReturn(person);
        Person savedPerson = personService.save(person);
        Mockito.verify(personDAO).save(person);
        assertEquals(person, savedPerson);
    }

    @Test
    void delete() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");

        personService.delete(person);
        Mockito.verify(personDAO).delete(person);
    }

    @Test
    void list() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");
        Person person2 = new Person();
        person2.setFirstName("Martha");
        person2.setLastName("Ruiz");

        List<Person> persons = Arrays.asList(person, person2);
        Mockito.when(personDAO.findAll()).thenReturn(persons);

        Iterable<Person> result = personService.list();
        Mockito.verify(personDAO).findAll();
        assertTrue(result.iterator().hasNext());
    }

    @Test
    void findById() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");
        Person person2 = new Person();
        person2.setFirstName("Martha");
        person2.setLastName("Ruiz");
        Person person3 = new Person();
        person3.setFirstName("Lucia");
        person3.setLastName("Castro");
        List<Person> persons = Arrays.asList(person, person2, person3);

        Mockito.when(personDAO.findById(2)).thenReturn(Optional.of(person2));

        Optional<Person> result = personService.findById(2);
        Mockito.verify(personDAO).findById(2);
        assertTrue(result.isPresent());

    }
}