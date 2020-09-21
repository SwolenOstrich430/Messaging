import fetch from "cross-fetch";
import { ApolloClient, HttpLink, InMemoryCache } from '@apollo/client';
import { setContext } from '@apollo/client/link/context';


const httpLink = new HttpLink({ uri: "http://localhost:8080/graphql", fetch});
console.log("running the file");
const authLink = setContext((_, { headers }) => {
    // get the authentication token from local storage if it exists
    // const token = localStorage.getItem("token");
    // return the headers to the context so httpLink can read them
    const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNeVRlc3RVc2VyIiwiZW1haWwiOiJ0ZXN0dXNlckB0ZXN0LmNvbSIsInJvbGVzIjoidXNlciIsImlkIjo4fQ.NtcMsZR1Xm0h7Hdoush5_B2PJuiW3wLKp45se7cXBLENWgoLzg919oxUbEd_kM0MkZCI6pnYylMI6vwUmG5mUA";
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
