package com.poc.neo4j.controllers;

import com.poc.neo4j.domain.Person;
import com.poc.neo4j.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author miguel.reyes on 9/16/16.
 */
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable("id") Long id) {
        return personService.find(id);
    }

    @GetMapping(value = "/people")
    public List<Person> getPeople() {
        return (List<Person>) personService.findAll();
    }

    @PostMapping(value = "/people")
    public Person savePerson(@RequestBody Person person) {
        Person orUpdate = personService.createOrUpdate(person);
        return orUpdate;
    }

    @DeleteMapping(value = "/people/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        personService.delete(id);
    }



}
