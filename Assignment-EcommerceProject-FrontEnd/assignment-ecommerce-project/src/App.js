import React from "react";
import { BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import Header from "./common/Header";
function App() {
  return (
    <>
      <Router>
        <Header />
        <Routes>
          {/* <Route path="/" exact>

          </Route> */}
        </Routes>
      </Router>
    </>
  );
}

export default App;
