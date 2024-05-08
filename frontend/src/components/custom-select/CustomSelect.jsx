import React from "react";
import Select from "react-select";

function CustomSelect(props) {
  const colourStyles = {
    control: (provided) => ({
      ...provided,
      width: "259px",
      border: "0",
      height: "45px",
    }),
    option: (provided, state) => ({
      ...provided,
      width: "259px",
      backgroundColor: state.isFocused
        ? "#8367D8"
        : "white",
      color: state.isFocuse ? "white" : "black",
      ":hover": {
        backgroundColor: "#8367D8",
        color: "white",
      },
    }),
  };
  
  return (
    <Select
      defaultValue={props.items[0]}
      label="Single select"
      options={props.items}
      onChange={(choice) => props.onChange(choice)}
      styles={colourStyles}
    />
  );
}

export default CustomSelect;
