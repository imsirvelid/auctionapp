import React from "react";
import Button from "../button/Button";
import SocialMediaCard from "../social-media-card/SocialMediaCard";
import Input from "../text-input/Input";
import "./footer.css";

function Footer() {
  return (
    <footer>
      <div className="footer-container">
        <div className="ul-block">
          <p className="item-title">AUCTION</p>
          <ul>
            <li><a href="#">About us</a></li>
            <li><a href="#">Terms and Conditions</a></li>
            <li><a href="#">Privacy and Policy</a></li>
          </ul>
        </div>
        <div className="ul-block">
          <p className="item-title">GET IN TOUCH</p>
          <ul>
            <li>Call Us at +123 797-567-2535</li>
            <li>support@auction.com</li>
            <li>
              <SocialMediaCard />
            </li>
          </ul>
        </div>
        <div className="ul-block wider">
          <p className="item-title">NEWSLETTER</p>
          <ul>
            <li>
              Enter your email address and get notified about new products. We
              hate spam!
            </li>
            <li>
              {" "}
              <div className="simple-form">
                {" "}
                <Input /> <Button />{" "}
              </div>{" "}
            </li>
          </ul>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
