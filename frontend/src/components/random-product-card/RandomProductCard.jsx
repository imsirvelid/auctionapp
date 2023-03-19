import Button from "components/button/Button";
import React from "react";
import "./RandomProductCard.css";

function RandomProductCard(props) {
  return (
    <div className="random-product-card">
      <div className="random-product-content">
        <p className="random-product-name">{props.product.name}</p>
        <p className="purple-text">Start From - ${props.product.startingPrice}</p>
        <p>
          {props.product.details}
        </p>
        <Button type="default">BID NOW <i className="fa-solid fa-angle-right"></i></Button>
      </div>
      <div className="random-product-img">
        <img src={props.product.thumbnailUrl} />
      </div>
    </div>
  );
}

export default RandomProductCard;
