package com.poc.neo4j.executor;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author miguel.reyes on 9/14/16.
 */
class MovieServiceCypherExecutorImplTest {
    @Test
    public void findMovie() throws Exception {
        MovieServiceCypherExecutorImpl service = new MovieServiceCypherExecutorImpl("bolt://neo4j:1234@localhost");
        Map movie = service.findMovie("The Matrix");
        assertEquals("The Matrix", movie.get("title"));
        assertNotNull(movie.get("cast"));
    }

    @Test
    @Ignore
    public void testCreateMovie() throws Exception {
        MovieServiceCypherExecutorImpl service = new MovieServiceCypherExecutorImpl("bolt://neo4j:1234@localhost");
        service.createMovie("Austin Powers", "1999", "International man of mystery");

        Map movie = service.findMovie("Austin Powers");
        assertEquals("Austin Powers", movie.get("title"));
    }

}