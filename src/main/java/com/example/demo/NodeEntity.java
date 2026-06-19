package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("NodeEntity")
public class NodeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ConvertWith(converter = StringListConverter.class)
    private List<String> valueList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<String> getValueList() {
        return valueList;
    }
}
