package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.neo4j.test.autoconfigure.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.neo4j.Neo4jContainer;

@DataNeo4jTest
class DemoApplicationTests {

    private static Neo4jContainer neo4jContainer;

    @BeforeAll
    static void initializeNeo4j() {
        neo4jContainer = new Neo4jContainer("neo4j:5.26").withAdminPassword("somePassword");
        neo4jContainer.start();
    }

    @AfterAll
    static void stopNeo4j() {

        neo4jContainer.close();
    }

    @DynamicPropertySource
    static void neo4jProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
    }

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    @Test
    void repo() {
        assertTrue(nodeEntityRepository.findAll().isEmpty());

        NodeEntity entity = new NodeEntity();
        entity.getValueList().add("value1");
        entity.getValueList().add("value2");
        assertNull(entity.getId());

        entity = nodeEntityRepository.save(entity);
        assertNotNull(entity.getId());
        assertEquals(2, entity.getValueList().size());
    }
}
