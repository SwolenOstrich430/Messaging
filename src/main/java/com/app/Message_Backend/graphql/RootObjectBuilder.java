package com.app.Message_Backend.graphql;

import graphql.servlet.DefaultGraphQLRootObjectBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class RootObjectBuilder extends DefaultGraphQLRootObjectBuilder implements Supplier {

    public RootObjectBuilder() {
        super();
    }

    @Override
    public Object get() {
        return this;
    }
}
