import React from "react";
import "components/button/Button.css";

function Button(props) {
  return (
    <>
      <button className="primary">{props.text} {props.symbol}</button>
    </>
  );
}

export default Button;
