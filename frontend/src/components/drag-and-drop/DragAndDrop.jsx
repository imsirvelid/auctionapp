import React, {useState} from "react";
import "./DragAndDrop.css";
import {useRef} from "react";

function DragAndDrop(props) {
  const inputRef = useRef();

  const handleDragOver = (event) => {
    event.preventDefault();
  };

  const handleDrop = (event) => {
    event.preventDefault();
    props.files.set(event.dataTransfer.files);
  };

  const showImage = (image) => {
    return URL.createObjectURL(image);
  };
  return (
    <>
      {true && (
        <div
          className="dropdown-zone"
          onDragOver={handleDragOver}
          onDrop={handleDrop}
        >
          <span className="vertical-center-span">
            <p
              onClick={() => inputRef.current.click()}
              className="p-style-button"
            >
              Upload Photos
            </p>
            <p className="black-text">or just drag and drop</p>
            <p className="margin-text-0">(Add at least 3 photos)</p>
          </span>
          <input
            type="file"
            accept=".jpg,.png"
            multiple
            onChange={(event) => props.files.set(event.target.files)}
            hidden
            ref={inputRef}
          ></input>
        </div>
      )}
      {props.files.value.length !== 0 && (
        <div className="image-preview-div">
          <div className="image-preview-ul">
            {Array.from(props.files.value).map((file, index) => (
              <img
                key={index}
                src={showImage(file)}
                className="image-preview"
                onClick={(e) => props.featured.set(index)}
              ></img>
            ))}
          </div>
        </div>
      )}
    </>
  );
}

export default DragAndDrop;
