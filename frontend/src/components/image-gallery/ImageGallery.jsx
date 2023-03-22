import React from "react";
import ReactImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "./ImageGallery.css";

function ImageGallery(props) {
  return (
    <ReactImageGallery items={props.images} showNav={false} showPlayButton={false} showFullscreenButton={false} />
  )
}

export default ImageGallery