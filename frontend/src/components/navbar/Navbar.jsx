import React, {useContext, useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSearch, faBell} from "@fortawesome/free-solid-svg-icons";
import Input from "components/text-input/Input";
import SocialMediaCard from "components/social-media-card/SocialMediaCard";
import "./Navbar.css";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {UserContext} from "context/UserContext";
import Notification from "components/notification/Notification";

const DEFAULT_NOTIFICATION = {
  image:
    "https://cutshort-data.s3.amazonaws.com/cloudfront/public/companies/5809d1d8af3059ed5b346ed1/logo-1615367026425-logo-v6.png",
  message: "Notification one.",
  detailPage: "/events",
  receivedTime: "12h ago"
};

function Navbar() {
  const {user, setUser} = useContext(UserContext);
  const [data, setData] = useState([DEFAULT_NOTIFICATION]);
  const [inputValue, setInputValue] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const [path, setPath] = useState("");

  const searchHandler = (e) => {
    e.preventDefault();
    navigate("/search?name=" + inputValue);
  };

  const handleLogout = (e) => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    navigate("");
    setUser(null);
  };

  useEffect(() => {
    setPath(location.pathname);
    if (location.pathname === "/") setInputValue("");
  }, [location.pathname]);

  return (
    <nav>
      <div className="black-navbar">
        <div className="navbar-container">
          <div>
            <SocialMediaCard />
          </div>
          <div className="div-right">
            {user ? (
              <div className="login-options">
                  <Notification></Notification>
                <p className="login-options">
                  {`Hi, ${user.name} ${user.surname}`} &nbsp; &nbsp;{" "}
                  <span className="custom-p-button" onClick={handleLogout}>
                    Logout
                  </span>
                </p>
              </div>
            ) : (
              <p className="login-options">
                <Link to="user/login">Login</Link> or{" "}
                <Link to="user/register">create account</Link>
              </p>
            )}
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
            <Link to="" className={`${path === "/" ? "selected-route" : ""}`}>
              HOME
            </Link>
            <Link
              to="/search"
              className={`${
                path === "/search" || path.slice(0, 9) === "/products"
                  ? "selected-route"
                  : ""
              }`}
            >
              SHOP
            </Link>
            {user && (
              <Link
                to="/user/profile"
                className={`${
                  path === "/user/profile" ? "selected-route" : ""
                }`}
              >
                MY ACCOUNT
              </Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
