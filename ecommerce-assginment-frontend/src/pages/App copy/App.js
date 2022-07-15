import React, { Component } from "react";
import { Route, Switch } from "react-router-dom";
import Footer from "../../component/Footer/Footer";
import Home from "../Home/Home";
import NavBar from "../../component/NavBar/NavBar";

class App extends Component {
  render() {
    return (
      <div>
        <NavBar />
        <Switch>
          <Route exact path="/" component={Home} />
        </Switch>
        <Footer />
      </div>
    );
  }
}
