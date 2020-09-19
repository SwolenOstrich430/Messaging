package com.app.Message_Backend.graphql;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.GraphQLInvocationInputFactory;
import graphql.servlet.GraphQLRootObjectBuilder;
import graphql.servlet.GraphQLSchemaProvider;

import java.util.function.Supplier;


public class InvocationInputFactory extends GraphQLInvocationInputFactory {
    public InvocationInputFactory(Supplier<GraphQLSchemaProvider> schemaProviderSupplier,
                                  Supplier<GraphQLContextBuilder> contextBuilderSupplier,
                                  Supplier<GraphQLRootObjectBuilder> rootObjectBuilderSupplier) {
        super(schemaProviderSupplier, contextBuilderSupplier, rootObjectBuilderSupplier);
    }

}
