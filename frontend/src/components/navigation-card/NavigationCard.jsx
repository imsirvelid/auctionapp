import React from "react";
import "./NavigationCard.css";
import { Link } from "react-router-dom";

function NavigationCard({ name, link, subLink, linkTo }) {
  return (
    <div className="current-location">
      <div className="current-location-flexdiv">
        <p>{name}</p>
        <p className="right-p-link">
          <Link to={linkTo}>{link}</Link> <span className="slash"> / </span><span className="sublink-span">{subLink}</span>
        </p>
      </div>
    </div>
  );
}

export default NavigationCard;
