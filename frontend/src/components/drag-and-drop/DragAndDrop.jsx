import React from "react";
import "./DragAndDrop.css";
import {useRef} from "react";
import {faStar, faTrash} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

function DragAndDrop(props) {
  const inputRef = useRef();

  const handleDragOver = (event) => {
    event.preventDefault();
  };

  const handleDrop = (event) => {
    event.preventDefault();
    props.files.set(event.dataTransfer.files);
  };

  const removeImage = (index) => {
    const files = Array.from(props.files.value);
    files.splice(index, 1);

    const newFileList = new DataTransfer();

    files.forEach(function (file) {
      newFileList.items.add(file);
    });
    props.files.set(newFileList.files);
  };

  const showImage = (image) => {
    return URL.createObjectURL(image);
  };
  return (
    <>
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
            <p className="margin-text-0">(Add at least {props.minImages} photos)</p>
          </span>
          <input
            type="file"
            accept="image/*"
            multiple
            onChange={(event) => props.files.set(event.target.files)}
            hidden
            ref={inputRef}
          />
        </div>
      {props.files.value.length !== 0 && (
        <div className="image-preview-div">
          <div className="image-preview-ul">
            {Array.from(props.files.value).map((file, index) => (
              <div key={index} className="image-ul-container">
                <img
                  src={showImage(file)}
                  className="image-preview"
                  alt="preview"
                />
                <div className="image-options">
                  <div>
                    <FontAwesomeIcon
                      icon={faStar}
                      onClick={() => props.featured.set(index)}
                      className={`clickable-icon ${props.featured.value === index && "orange-icon"}`}
                    />
                  </div>
                  <div>
                    <FontAwesomeIcon
                      icon={faTrash}
                      onClick={() => removeImage(index)}
                      className="red-icon clickable-icon"
                    />
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
    </>
  );
}

export default DragAndDrop;
