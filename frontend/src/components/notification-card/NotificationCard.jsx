import React from "react";
import "./NotificationCard.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faTrash} from "@fortawesome/free-solid-svg-icons";

function NotificationCard({data, trashFunction, checkFunction}) {
  const {id, image, userId, productId, type, message, receivedTime} = data;

  const handleCheck = (id) => {
    checkFunction(id);
  };

  const handleDelete = (id) => {
    trashFunction(id);
  };

  const payProduct = (id) => {
    console.log("Pay for: ", id);
  }

  return (
    <>
      {type === "SUCCESS" ? (
        <div className="notification-card" onClick={() => payProduct(id)}>
          <img src={image} alt="notification" />
          <div className="notification-card-sub">
            <p className="notification-message">{message}</p>
            <p className="received-date-notification">{receivedTime}</p>
          </div>
          <div className="notification-options">
            <FontAwesomeIcon onClick={() => handleDelete(id)} icon={faTrash} />
            <FontAwesomeIcon onClick={() => handleCheck(id)} icon={faCheck} />
          </div>
        </div>
      ) : (
        <div className="notification-card">
          <img src={image} alt="notification" />
          <div className="notification-card-sub">
            <p className="notification-message">{message}</p>
            <p className="received-date-notification">{receivedTime}</p>
          </div>
          <div className="notification-options">
            <FontAwesomeIcon onClick={() => handleDelete(id)} icon={faTrash} />
            <FontAwesomeIcon onClick={() => handleCheck(id)} icon={faCheck} />
          </div>
        </div>
      )}
    </>
  );
}

export default NotificationCard;
