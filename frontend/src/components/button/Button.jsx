import React from "react";
import "components/button/Button.css";

function Button(props) {
  return (
    <>
      <button className="primary">{props.children}</button>
    </>
  );
}

export default Button;
