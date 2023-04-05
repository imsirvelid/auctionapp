import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faTwitter, faInstagram, faGooglePlus } from "@fortawesome/free-brands-svg-icons";
import "./SocialMediaCard.css";

function SocialMediaCard() {
  return (
    <div className="social-media-card">
      <a href="https://www.facebook.com" target="_blank" rel="noreferrer"><FontAwesomeIcon className="i-social-media first" icon={ faFacebook } /></a>
      <a href="https://www.instagram.com" target="_blank" rel="noreferrer"><FontAwesomeIcon className="i-social-media" icon={ faInstagram } /></a>
      <a href="https://www.twitter.com" target="_blank" rel="noreferrer"><FontAwesomeIcon className="i-social-media" icon={ faTwitter } /></a>
      <a href="https://www.google.com" target="_blank" rel="noreferrer"><FontAwesomeIcon className="i-social-media" icon={ faGooglePlus } /></a>
    </div>
  );
}

export default SocialMediaCard;
