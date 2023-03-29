import React from "react";
import ReactImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "./ImageGallery.css";

function ImageGallery(props) {
  var images = [];
  for (var prop in props.images){
    images.push({original: props.images[prop].imageUrl, thumbnail: props.images[prop].imageUrl});
  }
  return (
    <ReactImageGallery items={images} showNav={false} showPlayButton={false} showFullscreenButton={false} />
  )
}

export default ImageGallery