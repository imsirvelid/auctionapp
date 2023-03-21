import React from "react";
import "./Input.css";

function Input(props) {
  return (
    <input
      className="custom-input-field"
      type="text"
      placeholder={props.placeholder}
      style={{ width: `${props.width}`}}
    />
  );
}

export default Input;
