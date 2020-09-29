import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import './App.css';
import { ApolloProvider } from "@apollo/client";
import Messaging from "./components/Messaging";
import client from "./graphql/index";

function App() {
  return (
    <ApolloProvider client={client}>
      <div className="app-main-container">
        <Router>
          <Route exact path="/messaging" component={Messaging}/>
        </Router>

      </div>
    </ApolloProvider>
  );
}

export default App;
