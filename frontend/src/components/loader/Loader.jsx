import React from "react";
import {MagnifyingGlass} from "react-loader-spinner";

function Loader(props) {
  return (
    <MagnifyingGlass
      visible={props.visible}
      height={props.height}
      width={props.width}
      ariaLabel="MagnifyingGlass-loading"
      wrapperStyle={{}}
      wrapperClass="MagnifyingGlass-wrapper"
      glassColor={props.glassColor}
      color={props.color}
    />
  );
}

export default Loader;
