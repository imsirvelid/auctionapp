import React from "react";
import { Link } from "react-router-dom";

import "./ProductGridCard.css";

function ProductGridCard(props) {
  return (
    <Link to={props.linkTo}>
    <div className="product-grid-card">
      <div className="product-image-container">
        <img src={props.thumbnailUrl} alt="product-thumbnail" />
      </div>
      <h3 className="product-title">{props.productTitle}</h3>
      <p>Starts from ${props.startsFrom}</p>
    </div>
    </Link>
  );
}

export default ProductGridCard;
