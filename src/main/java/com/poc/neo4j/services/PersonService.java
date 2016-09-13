package com.poc.neo4j.services;

import com.poc.neo4j.domain.Person;
import org.springframework.stereotype.Service;

/**
 * @author miguel.reyes on 9/16/16.
 */
@Service
public class PersonService extends GenericService<Person> {

    @Override
    public Class<Person> getEntityType() {
        return Person.class;
    }
}
