package com.poc.neo4j.services;

import com.poc.neo4j.Neo4jSessionFactory;
import com.poc.neo4j.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericService<T> implements Service<T> {

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;

    @Autowired
    private Neo4jSessionFactory sessionFactory;

    @Override
    public Iterable<T> findAll() {
        return sessionFactory.getNeo4jSession().loadAll(getEntityType(), DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return sessionFactory.getNeo4jSession().load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getNeo4jSession().delete(sessionFactory.getNeo4jSession().load(getEntityType(), id));
    }

    @Override
    public T createOrUpdate(T entity) {
        sessionFactory.getNeo4jSession().save(entity, DEPTH_ENTITY);
        return find(((Entity) entity).getId());
    }

    public abstract Class<T> getEntityType();
}
