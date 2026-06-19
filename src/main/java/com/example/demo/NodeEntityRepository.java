package com.example.demo;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NodeEntityRepository extends Neo4jRepository<NodeEntity, Long> {
    
}
