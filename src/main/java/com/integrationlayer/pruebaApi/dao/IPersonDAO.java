package com.integrationlayer.pruebaApi.dao;

import com.integrationlayer.pruebaApi.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonDAO extends CrudRepository<Person, Integer> {
}
