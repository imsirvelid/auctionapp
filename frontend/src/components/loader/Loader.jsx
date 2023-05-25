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
      glassColor="#ECECEC"
      color="#8367D8"
    />
  );
}

export default Loader;
