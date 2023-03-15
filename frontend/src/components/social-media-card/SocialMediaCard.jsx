import React from "react";
import "./SocialMediaCard.css";

function SocialMediaCard() {
  return (
    <div>
      <a href="https://www.facebook.com"><i className="fa-brands fa-facebook first"></i></a>
      <a href="https://www.instagram.com"><i className="fa-brands fa-instagram"></i></a>
      <a href="https://www.twitter.com" ><i className="fa-brands fa-twitter"></i></a>
      <a href="https://www.google.com"><i className="fa-brands fa-google-plus-g"></i></a>
    </div>
  );
}

export default SocialMediaCard;
