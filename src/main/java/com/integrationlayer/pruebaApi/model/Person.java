package com.integrationlayer.pruebaApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Person {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idperson")
    private Integer idPerson;

    @NotNull
    @Setter
    @Column(name="firstname", nullable = false, length=70)
    private String firstName;

    @NotBlank
    @Setter
    @Column(name="lastname", nullable = false, length=70)
    private String lastName;
}
