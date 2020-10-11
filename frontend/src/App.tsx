import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import './App.css';
import { ApolloProvider } from "@apollo/client";
import Messaging from "./components/Messaging";
import Home from "./components/Home";
import client from "./graphql/index";
import { Provider } from "react-redux";
import store from "./store";


function App() {
  return (
    <Provider store={store}>
      <ApolloProvider client={client}>
        <div className="app-main-container">
          <Router>
            <Route exact path="/" component={Home}/>
            <Route exact path="/messaging" component={Messaging}/>
          </Router>
        </div>
      </ApolloProvider>
    </Provider>
  );
}

export default App;
