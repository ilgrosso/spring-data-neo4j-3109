package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyConverter;
import tools.jackson.core.type.TypeReference;

abstract class SerializableListConverter<T extends Serializable> implements Neo4jPersistentPropertyConverter<List<T>> {

    protected abstract TypeReference<List<T>> typeRef();

    @Override
    public Value write(final List<T> source) {
        return Optional.ofNullable(source).map(POJOHelper::serialize).map(Values::value).
                orElse(Values.value(List.of()));
    }

    @Override
    public List<T> read(final Value source) {
        return Optional.ofNullable(source).
                map(data -> POJOHelper.deserialize(source.asString(), typeRef())).orElseGet(ArrayList::new);
    }
}
