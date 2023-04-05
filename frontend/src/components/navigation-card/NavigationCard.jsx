import React from "react";
import "./NavigationCard.css";

function NavigationCard({ name, link, subLink }) {
  return (
    <div className="current-location">
      <div className="current-location-flexdiv">
        <p>{name}</p>
        <p className="right-p-link">
          {link} <span className="slash"> / </span><span className="sublink-span">{subLink}</span>
        </p>
      </div>
    </div>
  );
}

export default NavigationCard;
