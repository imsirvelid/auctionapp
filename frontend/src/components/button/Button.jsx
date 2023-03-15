import React from 'react';
import symbolsGoDefault from '../../assets/img/symbols-go-default.png';
import "./Button.css";

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
