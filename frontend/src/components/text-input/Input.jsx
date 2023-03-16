import React from "react";
import "components/text-input/Input.css";

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
