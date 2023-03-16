import React from "react";
import SocialMediaCard from "components/social-media-card/SocialMediaCard";
import "components/navbar/Navbar.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import Input from "../text-input/Input";

function Navbar() {
  return (
    <nav>
      <div className="black-navbar">
        <div className="navbar-container">
          <div>
              <SocialMediaCard />            
          </div>
          <div className="div-right">
            <p className="login-options"> Hi, John Doe </p>
          </div>
        </div>
      </div>
      <div className="white-navbar">
        <div className="navbar-container">
          <div className="navbar-logo">
            <i className="fa-solid fa-hammer" />
            AUCTION
          </div>
          <div className="navbar-search-input">
            <Input width="95%" placeholder="Try enter: Shoes" ></Input>
            <FontAwesomeIcon className="search-icon" icon={ faSearch } />
          </div>
          <div className="navbar-routes">
            <a href="/about">HOME</a>
            <a href="/about">SHOP</a>
            <a href="/about">MY ACCOUNT</a>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
