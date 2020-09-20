package com.app.Message_Backend;


import com.app.Message_Backend.auth.AuthenticationUtils;
import com.app.Message_Backend.auth.JwtUtils;
import com.app.Message_Backend.graphql.InvocationInputFactory;
import com.app.Message_Backend.graphql.SchemaProvider;
import com.app.Message_Backend.resolver.Mutation;
import com.app.Message_Backend.resolver.Query;
import com.app.Message_Backend.resolver.Subscription;
import com.app.Message_Backend.resolver.UserResolver;
import com.app.Message_Backend.service.ConversationService;
import com.app.Message_Backend.service.MessageService;
import com.app.Message_Backend.service.UserService;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.GraphQLRootObjectBuilder;
import graphql.servlet.SimpleGraphQLHttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;


@SpringBootApplication
public class WorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkshopApplication.class, args);
    }

    // TODO: WHY THE FUCK AM I HARD CODING ALL OF THIS....
    @Autowired
    private UserService userService;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationUtils authenticationUtils;
    @Autowired
    private Supplier<GraphQLContextBuilder> contextBuilder;
    @Autowired
    private Supplier<GraphQLRootObjectBuilder> rootObjectBuilder;

    @Bean
    public ServletRegistrationBean graphQLServlet() {
        return new ServletRegistrationBean(SimpleGraphQLHttpServlet
                .newBuilder(getInvocationInputFactory()).build(), "/graphql");
    }

    public InvocationInputFactory getInvocationInputFactory() {
        return new InvocationInputFactory(new SchemaProvider(buildSchema(userService, conversationService,
                messageService, jwtUtils, authenticationUtils)),
                contextBuilder,
                rootObjectBuilder);
    }

    private static GraphQLSchema buildSchema(UserService userService, ConversationService conversationService,
                                             MessageService messageService, JwtUtils jwtUtils,
                                             AuthenticationUtils authenticationUtils) {

        return SchemaParser.newParser()
                .file("graphql/schema.graphqls")
                .resolvers(new Query(userService,  jwtUtils, authenticationUtils),
                        new Mutation(userService, conversationService, messageService, authenticationUtils),
                        new Subscription(),
                        new UserResolver(userService))
                .build()
                .makeExecutableSchema();
    }
}
