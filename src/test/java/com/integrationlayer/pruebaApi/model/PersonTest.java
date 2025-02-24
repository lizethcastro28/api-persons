package com.integrationlayer.pruebaApi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getIdPerson() {
        Person person = new Person();
        person.setIdPerson(1);
        Integer idPerson = new Person().getIdPerson();
        assertEquals(1, person.getIdPerson());
    }

    @Test
    void setIdPerson() {
        Integer idPerson = 1;
        Person person = new Person();
        person.setIdPerson(idPerson);
        assertEquals(idPerson, person.getIdPerson());
    }

    @Test
    void getFirstName() {
        Person person = new Person();
        person.setFirstName("Jose");
        assertEquals("Jose", person.getFirstName());
    }

    @Test
    void setFirstName() {
        Person person = new Person();
        person.setFirstName("Jose");
        assertEquals("Jose", person.getFirstName());
    }

    @Test
    void getLastName() {
        Person person = new Person();
        person.setLastName("Castro");
        assertEquals("Castro", person.getLastName());
    }

    @Test
    void setLastName() {
        Person person = new Person();
        person.setLastName("Castro");
        assertEquals("Castro", person.getLastName());
    }
}