import React from "react";
import SocialMediaCard from "../social-media-card/SocialMediaCard";
import "./navbar.css";

function Header() {
  return (
    <nav>
      <div className="black-navbar">
        <div className="navbar-container">
          <div>
            <a href="default.asp">
              <SocialMediaCard />
            </a>
          </div>
          <div className="div-right">
            <p className="login-options"> Hi, John Doe </p>
          </div>
        </div>
      </div>
      <div className="white-navbar">
        <div className="navbar-container">
          <div className="navbar-logo">
            <i class="fa-solid fa-hammer"></i>
            AUCTION
          </div>
          <div className="navbar-search-input">
            <input type="text" placeholder="Try enter: Shoes" />{" "}
            <i class="fa-sharp fa-solid fa-magnifying-glass"></i>
          </div>
          <div className="navbar-routes">
            <a href="#">HOME</a>
            <a href="#">SHOP</a>
            <a href="#">MY ACCOUNT</a>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Header;
