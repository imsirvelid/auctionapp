import React from "react";
import "./Input.css";

function Input(props) {
  return (
    <input
      className={`custom-input-field ${props.type}`}
      type="text"
      placeholder={props.placeholder}
      name={props.name}
      style={{ width: `${props.width}`}}
      onChange = {props.onChange}
      value = {props.value}
    />
  );
}

export default Input;
