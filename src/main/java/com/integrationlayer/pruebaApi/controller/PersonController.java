package com.integrationlayer.pruebaApi.controller;

import com.integrationlayer.pruebaApi.model.Person;
import com.integrationlayer.pruebaApi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/saludo")
    @Operation(summary = "Saludo", description = "Imprimir un saludo")
    public String saludo() {
        logger.info("Saludo");
        return "Prueba Docker Spring Boot";
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        Iterable<Person> personsIterable = personService.list();
        List<Person> personsList = new ArrayList<>();

        for (Person person : personsIterable) {
            personsList.add(person);
        }

        return personsList.isEmpty()
                ? ResponseEntity.ok(Collections.emptyList())
                : ResponseEntity.ok(personsList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(
            @PathVariable
            @Parameter(name="id", description = "id person search", example = "3") Integer id) {
        Optional<Person> personOptional = personService.findById(id);

        return personOptional.map(person -> ResponseEntity.ok(person))
                .orElse(ResponseEntity.ok().build());
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody Person person) {
        try {
            Person savedPerson = personService.save(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }
}