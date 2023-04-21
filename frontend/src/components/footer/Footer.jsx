import React from "react";
import {Link} from "react-router-dom";
import Button from "components/button/Button";
import SocialMediaCard from "components/social-media-card/SocialMediaCard";
import Input from "components/text-input/Input";
import "./Footer.css";

function Footer() {
  return (
    <footer>
      <div className="footer-container">
        <div className="ul-block">
          <p className="item-title">AUCTION</p>
          <ul className="footer-ul-item">
            <li className="footer-li-item">
              <Link to="/about">About Us</Link>
            </li>
            <li className="footer-li-item">
              <Link to="/terms-and-conditions">Terms and Conditions</Link>
            </li>
            <li className="footer-li-item">
              <Link to="/privacy-policy">Privacy and Policy</Link>
            </li>
          </ul>
        </div>
        <div className="ul-block">
          <p className="item-title">GET IN TOUCH</p>
          <ul className="footer-ul-item">
            <li className="footer-li-item"><a href="tel:+1237975672535">Call Us at +123 797-567-2535</a></li>
            <li className="footer-li-item"><a href="mailto:support@auction.com">support@auction.com</a></li>
            <li className="footer-li-item">
              <SocialMediaCard />
            </li>
          </ul>
        </div>
        <div className="ul-block wider">
          <p className="item-title">NEWSLETTER</p>
          <ul className="footer-ul-item">
            <li className="footer-li-item">
              Enter your email address and get notified about new products. We
              hate spam!
            </li>
            <li className="footer-li-item">
              <div className="simple-form">
                <Input />
                <Button type="primary">GO <i className="fa-solid fa-angle-right"></i></Button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
