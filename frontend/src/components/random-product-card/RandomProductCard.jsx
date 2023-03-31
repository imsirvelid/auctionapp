import React from "react";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import "./RandomProductCard.css";
import Button from "components/button/Button";
function RandomProductCard(props) {
  return (
    <Link to={`/products/${props.product.id}`}>
    <div className="random-product-card">
      <div className="random-product-content">
        <p className="random-product-name">{props.product.name}</p>
        <p className="purple-text">Start From - ${props.product.startingPrice}</p>
        <p>
          {props.product.details}
        </p>
        <Button type="default">BID NOW <FontAwesomeIcon icon={ faAngleRight } /></Button>
      </div>
      <div className="random-product-img">
        <img src={props.product.images[0].imageUrl} alt="product-img" />
      </div>
    </div>
    </Link>
  );
}

export default RandomProductCard;
