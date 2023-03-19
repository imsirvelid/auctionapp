import React from "react";
import "components/button/Button.css";

function Button(props) {
  return (
      <button className={`button ${props.type}`}>{props.children}</button>
  );
}

export default Button;
