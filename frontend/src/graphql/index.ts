import fetch from "cross-fetch";
import { ApolloClient, HttpLink, InMemoryCache } from '@apollo/client';
import { setContext } from '@apollo/client/link/context';


const httpLink = new HttpLink({ uri: "http://localhost:8080/graphql", fetch});
console.log("running the file");
const authLink = setContext((_, { headers }) => {
    // get the authentication token from local storage if it exists
    const token = localStorage.getItem("token");
    // return the headers to the context so httpLink can read them
    console.log(localStorage.getItem("token"));
    return {
      headers: {
        ...headers,
        authorization: token ? `Bearer ${token}` : "",
      }
    }
  });

const client = new ApolloClient({
    link: authLink.concat(httpLink), 
    cache: new InMemoryCache()
});


export default client;
