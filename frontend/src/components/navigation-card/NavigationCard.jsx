import React from "react";
import "./NavigationCard.css";

function NavigationCard({ name, link, subLink }) {
  return (
    <div className="current-location">
      <div style={{ display: "flex" }}>
        <p style={{ "fontWeight": "500" }}>{name}</p>
        <p style={{ "fontWeight": "100", "marginLeft": "auto" }}>
          {link}/<span style={{ "fontWeight": "500", "marginLeft": "10px" }}>{subLink}</span>
        </p>
      </div>
    </div>
  );
}

export default NavigationCard;
