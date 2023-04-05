import React from "react";
import ReactImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "./ImageGallery.css";

function ImageGallery(props) {
  const images = props.images.map((img) => ({
    original: img.url,
    thumbnail: img.url,
  }));
  return (
    <ReactImageGallery
      items={images}
      showNav={false}
      showPlayButton={false}
      showFullscreenButton={false}
    />
  );
}

export default ImageGallery;
