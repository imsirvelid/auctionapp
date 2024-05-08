import React, {useState} from "react";
import "./NotificationCard.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faTrash} from "@fortawesome/free-solid-svg-icons";
import {getPaymentIntent} from "api/Payment";
import {ThreeDots} from "react-loader-spinner";

function NotificationCard({data, trashFunction, checkFunction}) {
  const [spinner, setSpinner] = useState(false);
  const {id, image, userId, productId, type, message, receivedTime} = data;

  const handleCheck = (id) => {
    checkFunction(id);
  };

  const handleDelete = (id) => {
    trashFunction(id);
  };

  const payProduct = (productId, id) => {
    setSpinner(true);
    getPaymentIntent(productId).then((response) => {
      window.location.replace(response);
      handleCheck(id);
      setSpinner(false);
    });
  };

  return (
    <>
      {spinner === true ? (
        <div className="spinner-middle-container">
          <ThreeDots
            height="80"
            width="80"
            radius="9"
            color="#8367D8"
            ariaLabel="three-dots-loading"
            wrapperStyle={{}}
            wrapperClassName=""
            visible={true}
          />
        </div>
      ) : type === "SUCCESS" ? (
        <div
          className="notification-card"
          onClick={() => payProduct(productId, id)}
        >
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
