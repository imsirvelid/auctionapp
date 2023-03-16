import React from "react";
import SocialMediaCard from "components/social-media-card/SocialMediaCard";
import "components/navbar/Navbar.css";

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
          <div className="navbar-search-input" style={{ "visibility": "hidden"}}>
            <input type="text" placeholder="Try enter: Shoes" />{" "}
            <i className="fa-sharp fa-solid fa-magnifying-glass" />
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
