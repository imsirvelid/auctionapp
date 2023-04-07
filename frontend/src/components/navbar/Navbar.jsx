import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSearch} from "@fortawesome/free-solid-svg-icons";
import Input from "components/text-input/Input";
import SocialMediaCard from "components/social-media-card/SocialMediaCard";
import "./Navbar.css";
import {Link, useNavigate} from "react-router-dom";

function Navbar() {
  const [inputValue, setInputValue] = useState("");
  const navigate = useNavigate();

  const searchHandler = (e) => {
    e.preventDefault();
    navigate("/search?name="+inputValue)
    setInputValue("");
  }

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
            <Link className="link-component" to="/">
              <i className="fa-solid fa-hammer" />
              AUCTION
            </Link>
          </div>
            <form className="navbar-search-input" onSubmit={searchHandler}>
            <Input
              width="95%"
              placeholder="Try enter: Shoes"
              value={inputValue}
              onChange={(evt) => setInputValue(evt.target.value)}
            />
            <Link type="submit" to={`/search?name=${inputValue}`}>
              <FontAwesomeIcon className="search-icon" icon={faSearch} />
            </Link>
          </form>
          <div className="navbar-routes">
            <Link to="">HOME</Link>
            <Link to="/search">SHOP</Link>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
