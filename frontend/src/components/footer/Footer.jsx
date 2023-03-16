import React from "react";
import Button from "components/button/Button";
import SocialMediaCard from "components/social-media-card/SocialMediaCard";
import Input from "components/text-input/Input";
import {Link} from "react-router-dom";
import "components/footer/Footer.css";

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
            <li className="footer-li-item">Call Us at +123 797-567-2535</li>
            <li className="footer-li-item">support@auction.com</li>
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
                <Button
                  text="GO"
                  symbol={<i className="fa-solid fa-angle-right"></i>}
                />
              </div>
            </li>
          </ul>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
