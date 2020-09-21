import React from 'react';
import './App.css';
import { ApolloProvider } from "@apollo/client";

import client from "./graphql/index";

function App() {
  return (
    <ApolloProvider client={client}> 
      <div>Hello, world!</div>
    </ApolloProvider>
  );
}

export default App;
