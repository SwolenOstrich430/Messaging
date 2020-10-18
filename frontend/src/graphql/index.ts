import fetch from "cross-fetch";
import { ApolloClient, HttpLink, InMemoryCache, split } from '@apollo/client';
import { setContext } from '@apollo/client/link/context';
import { WebSocketLink } from '@apollo/client/link/ws'
import { getMainDefinition } from '@apollo/client/utilities';

const wsLink = new WebSocketLink({
    uri: "ws://localhost:8080/subscriptions",
    options: {
      reconnect: true,
      connectionParams: {
        authToken: localStorage.getItem("token")
      }
    }
});

const httpLink = new HttpLink({ uri: "/graphql", fetch});

const authLink = setContext((_, { headers }) => {
    // get the authentication token from local storage if it exists
    // const token = localStorage.getItem("token");
    let token = localStorage.getItem("token");
    return {
      headers: {
        ...headers,
        authorization: token ? `Bearer ${token}` : "",
      }
    }
  });

const splitLink = split(
    ({ query }) => {
      const definition = getMainDefinition(query);
        return (
          definition.kind === 'OperationDefinition' &&
          definition.operation === 'subscription'
      );
    },
    wsLink,
    authLink.concat(httpLink),
);

const client = new ApolloClient({
    link: splitLink, 
    cache: new InMemoryCache()
});


export default client;
