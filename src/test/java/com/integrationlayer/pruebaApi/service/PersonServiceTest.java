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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private IPersonDAO personDAO;

    @InjectMocks
    private PersonService personService;

    @Test
    void save() {
        Person person = new Person();
        person.setIdPerson(2);
        person.setFirstName("Jose");
        person.setLastName("Silva");

        Mockito.when(personDAO.save(person)).thenReturn(person);

        Person savedPerson = personService.save(person);

        verify(personDAO).save(person);
        assertEquals(person, savedPerson);

        assertNotNull(savedPerson);
        assertEquals(person.getIdPerson(), savedPerson.getIdPerson());
        verify(personDAO, times(1)).save(person);
    }

    @Test
    void save_ShouldThrowException_WhenPersonIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            personService.save(null);
        });

        assertEquals("La persona no puede ser nula", exception.getMessage());
        verify(personDAO, never()).save(any());
    }

    @Test
    void update() {
        // Crear objeto de prueba con ID asignado
        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Jose");
        person.setLastName("Silva");

        // Simular el comportamiento de personDAO.save()
        Mockito.when(personDAO.save(person)).thenReturn(person);

        // Llamar al método a probar
        Person savedPerson = personService.update(person);

        // Verificar que la persona fue guardada correctamente
        assertNotNull(savedPerson);
        verify(personDAO, times(1)).save(person);
        assertEquals(1, savedPerson.getIdPerson());
        assertEquals("Jose", savedPerson.getFirstName());
        assertEquals("Silva", savedPerson.getLastName());
    }


    @Test
    void delete() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");

        personService.delete(person);
        verify(personDAO).delete(person);
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
        verify(personDAO).findAll();
        assertTrue(result.iterator().hasNext());
    }

    @Test
    void empty_list() {
        Mockito.when(personDAO.findAll()).thenReturn(Collections.emptyList());

        Iterable<Person> result = personService.list();

        verify(personDAO).findAll();

        // Assert that the result is empty
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void findById() {
        Person person = new Person();
        person.setFirstName("Jose");
        person.setLastName("Silva");

        Mockito.when(personDAO.findById(1)).thenReturn(Optional.of(person));

        Optional<Person> result = personService.findById(1);
        verify(personDAO).findById(1);
        assertTrue(result.isPresent());

    }
    @Test
    void findById_invalidId_returnsEmptyOptional() {
        Optional<Person> result = personService.findById(-1); // Invalid ID
        assertFalse(result.isPresent());
    }
}