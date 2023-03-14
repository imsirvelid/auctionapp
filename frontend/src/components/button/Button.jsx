import React from 'react';
import symbolsGoDefault from '../../assets/img/symbols-go-default.png';
import "./button.css";

function Button() {
  return (
    <>
      <button>
        <img src={symbolsGoDefault} alt="" />
      </button>
    </>
  );
}

export default Button;
