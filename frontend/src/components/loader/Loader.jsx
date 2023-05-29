import React from "react";
import {MagnifyingGlass} from "react-loader-spinner";

function Loader(props) {
  return (
    <MagnifyingGlass
      visible={props.visible}
      height={props.height}
      width={props.width}
      glassColor="#ECECEC"
      color="#8367D8"
    />
  );
}

export default Loader;
