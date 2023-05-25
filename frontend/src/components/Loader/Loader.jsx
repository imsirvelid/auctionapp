import React from "react";
import {MagnifyingGlass} from "react-loader-spinner";

function Loader(props) {
  return (
    <MagnifyingGlass
      visible={props.visible}
      height={props.height}
      width={props.width}
      ariaLabel={props.ariaLabel}
      wrapperStyle={{}}
      wrapperClass={props.wrapperClass}
      glassColor={props.glassColor}
      color={props.color}
    />
  );
}

export default Loader;
