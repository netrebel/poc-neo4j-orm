package com.poc.neo4j;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class Neo4jSessionFactory {

    private final String URL = "bolt://neo4j:1234@localhost";

    private org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI(URL)
                .setCredentials("neo4j", "1234")
                .setEncryptionLevel("NONE")
                .setTrustStrategy("TRUST_ON_FIRST_USE")
                .setTrustCertFile("file:///tmp/cert");
        return config;
    }

    private Neo4jSessionFactory() {

    }

    public Session getNeo4jSession() {
        SessionFactory sessionFactory = new SessionFactory(getConfiguration(), "com.poc.neo4j.domain");
        return sessionFactory.openSession();
    }
}
