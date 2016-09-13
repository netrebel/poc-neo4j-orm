package com.poc.neo4j;

import com.poc.neo4j.domain.Movie;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.TestServer;

import static org.junit.Assert.assertTrue;

/**
 * @author miguel.reyes on 9/20/16.
 */
public class MovieServiceTest {

    private static TestServer testServer;

    @BeforeClass
    public static void setupTestServer() {
        testServer = new TestServer.Builder().build();
    }

    @AfterClass
    public static void teardownTestServer() {
        testServer.shutdown();
    }

    @Test
    @Ignore
    public void shouldCreateUser() {

        Movie movie = new Movie();
        movie.setTitle("Unit Test Movie");

        SessionFactory sessionFactory = new SessionFactory("com.poc.neo4j.domain");
        Session session = sessionFactory.openSession();
        session.save(movie);

        GraphDatabaseService db = testServer.getGraphDatabaseService();
        try (Transaction tx = db.beginTx()) {
            Result r = db.execute("MATCH (u:Movie {title: 'Unit Test Movie'}) RETURN u");
            assertTrue(r.hasNext());
            tx.success();
        }
    }
}
