package com.integrationlayer.pruebaApi.service;

import com.integrationlayer.pruebaApi.dao.IPersonDAO;
import com.integrationlayer.pruebaApi.model.Person;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Service
public class PersonService {

    private final IPersonDAO personDAO;

    /**
     * Saves a Person entity.
     *
     * @param person The Person entity to save.
     * @return The saved Person entity.
     */
    public Person save(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        return personDAO.save(person);
    }


    /**
     * Update a Person entity.
     * @param person The person entity to update.
     * @return The updated Person entity.
     */
    public Person update(Person person) {
        return personDAO.save(person);
    }

    /**
     * Delete a Person entity.
     * @param person The person entity to delete.
     */
    public void delete(Person person) {
        personDAO.delete(person);
    }

    /**
     * Retrieves a list of all persons.
     *
     * @return An Iterable containing all Person entities.
     */
    public Iterable<Person> list() {
        return personDAO.findAll();
    }

    /**
     * Finds a person by their ID.
     *
     * @param id The ID of the person to find.
     * @return An Optional containing the Person entity if found, or an empty Optional if not found.
     */
    public Optional<Person> findById(Integer id) {
        return personDAO.findById(id);
    }
}
