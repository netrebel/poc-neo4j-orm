package com.poc.neo4j.services;

import com.poc.neo4j.Neo4jSessionFactory;
import com.poc.neo4j.responses.D3Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * @author miguel.reyes on 9/19/16.
 */
@Service
public class GraphService {

    @Autowired
    private Neo4jSessionFactory sessionFactory;

    private D3Graph toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rels = new ArrayList<Map<String, Object>>();
        int i = 0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("title", row.get("movie"), "label", "movie"));
            int target = i;
            i++;
            String[] casts = (String[]) row.get("cast");
            List<String> strings = Arrays.asList(casts);
            for (Object name : strings) {
                Map<String, Object> actor = map("title", name, "label", "actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source", source, "target", target));
            }
        }
        D3Graph d3Graph = new D3Graph();
        d3Graph.nodes = nodes;
        d3Graph.links = rels;
        return d3Graph;
    }

    /**
     * Query: MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}
     *
     * @param limit Some limit
     * @return List of (Movie, List<Cast>)
     */
    public D3Graph graph(int limit) {
        Iterator<Map<String, Object>> result = sessionFactory.getNeo4jSession()
                .query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}",
                        map("limit", limit)).iterator();
        return toD3Format(result);
    }

}
