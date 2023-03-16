import React from "react";
import "components/social-media-card/SocialMediaCard.css";

function SocialMediaCard() {
  return (
    <div className="social-media-card">
      <a href="https://www.facebook.com" target="_blank" rel="noreferrer"><i className="fa-brands fa-facebook first i-social-media"></i></a>
      <a href="https://www.instagram.com" target="_blank" rel="noreferrer"><i className="fa-brands fa-instagram i-social-media"></i></a>
      <a href="https://www.twitter.com" target="_blank" rel="noreferrer"><i className="fa-brands fa-twitter i-social-media"></i></a>
      <a href="https://www.google.com" target="_blank" rel="noreferrer"><i className="fa-brands fa-google-plus-g i-social-media"></i></a>
    </div>
  );
}

export default SocialMediaCard;
