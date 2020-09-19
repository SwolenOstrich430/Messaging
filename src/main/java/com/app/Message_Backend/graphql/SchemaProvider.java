package com.app.Message_Backend.graphql;

import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultGraphQLSchemaProvider;

import java.util.function.Supplier;

public class SchemaProvider extends DefaultGraphQLSchemaProvider implements Supplier {
    public SchemaProvider(GraphQLSchema schema) {
        super(schema);
    }

    @Override
    public Object get() {
        return this;
    }
}
