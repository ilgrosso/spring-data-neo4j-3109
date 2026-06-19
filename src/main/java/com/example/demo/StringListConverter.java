package com.example.demo;

import java.util.List;
import tools.jackson.core.type.TypeReference;

public class StringListConverter extends SerializableListConverter<String> {

    protected static final TypeReference<List<String>> TYPEREF = new TypeReference<List<String>>() {
    };

    @Override
    protected TypeReference<List<String>> typeRef() {
        return TYPEREF;
    }
}
