import React from "react";
import "./Header.css";
import Search from "./Search";
import Navbar from "./Navbar";
import Head from "./Head";
const Header = () => {
  return (
    <>
      <Head />
      <Search />
      <Navbar />
    </>
  );
};
export default Header;
