import React from "react";
import "components/navigation-card/NavigationCard.css";

function NavigationCard({ name, link, subLink }) {
  return (
    <div className="current-location">
      <div className="current-location-flexdiv">
        <p>{name}</p>
        <p className="right-p-link">
          {link}/<span className="sublink-span">{subLink}</span>
        </p>
      </div>
    </div>
  );
}

export default NavigationCard;
