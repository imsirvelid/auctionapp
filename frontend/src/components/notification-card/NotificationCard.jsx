import React from "react";
import "./NotificationCard.css";
import {setNotificationAsReaded} from "api/Notifications";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faCheckCircle, faTrash} from "@fortawesome/free-solid-svg-icons";

function NotificationCard({data}) {
  const {id, image, message, receivedTime} = data;

  const setAsRead = (id) => {
    console.log("ID IS: ", id);
    setNotificationAsReaded(id);
  };

  return (
    <div className="notification-card" onClick={() => setAsRead(id)}>
      <img src={image} alt="notification" />
      <div className="notification-card-sub">
        <p className="notification-message">{message}</p>
        <p className="received-date-notification">{receivedTime}</p>
      </div>
      <div className="notification-options">
        <FontAwesomeIcon icon={faTrash} />
        <FontAwesomeIcon icon={faCheck} />
      </div>
    </div>
  );
}

export default NotificationCard;
