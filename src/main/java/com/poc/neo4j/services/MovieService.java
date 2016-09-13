package com.poc.neo4j.services;

import com.poc.neo4j.domain.Movie;
import org.springframework.stereotype.Service;

/**
 * @author miguel.reyes on 9/16/16.
 */
@Service
public class MovieService extends GenericService<Movie> {

    @Override
    public Class<Movie> getEntityType() {
        return Movie.class;
    }
}
