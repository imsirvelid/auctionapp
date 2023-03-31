import React from "react";

import "./ProductGridCard.css";

function ProductGridCard(props) {
  return (
    <div className="product-grid-card">
      <div className="product-image-container">
        <img src={props.thumbnailUrl} alt="product-thumbnail" />
      </div>
      <h3 className="product-title">{props.productTitle}</h3>
      <p>Starts from ${props.startsFrom}</p>
    </div>
  );
}

export default ProductGridCard;
