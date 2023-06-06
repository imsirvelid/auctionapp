import React, {useState, useEffect} from "react";
import { useNavigate } from "react-router-dom";
import "./Notification.css";
import notificationIcon from "assets/img/icons8-notification-96.png";
import Notifications from "react-notifications-menu";
import {deleteUserNotification, getUserUnreadNotifications, setNotificationAsReaded} from "api/Notifications";
import {NotificationManager, NotificationContainer} from "react-notifications";
import {Stomp} from "@stomp/stompjs";
import SockJS from "sockjs-client";
import infoIcon from "assets/img/icons8-info-144.png";
import successIcon from "assets/img/icons8-success-144.png";
import moment from "moment";
import NotificationCard from "components/notification-card/NotificationCard";

const images = [
  {key: "INFO", image: {infoIcon}},
  {key: "SUCCESS", image: {successIcon}},
];

function Notification() {
  const [notifications, setNotifications] = useState([]);
  const navigate = useNavigate();

  const viewAll = () => {
    navigate("/user/notifications");
  }

  const getIconByKey = (key) => {
    const icon = images.find((item) => item.key === key);
    if (key === "INFO")
      return icon.image.infoIcon;
    return icon.image.successIcon;
  };

  /*const sockJS = new SockJS("http://localhost:8080/ws-message");

  const stompClient = Stomp.over(() => sockJS);*/

  /*const createNotification = (message, type) => {
    console.log("Create it");
    return () => {
      switch (type) {
        case "INFO":
          NotificationManager.info(message);
          break;
        case "SUCCESS":
          NotificationManager.success(message, "Congrats");
          break;
      }
    };
  };

  stompClient.connect({}, () => {
    stompClient.subscribe("/user/queue", (message) => {
      const payload = JSON.parse(message.body);
      createNotification(payload.message, payload.type)();
      console.log("Nova poruka");
      const notification = {
        image: getIconByKey(payload.type),
        message: payload.message,
        receivedTime: moment(payload.date).format("DD-MM-YYYY"),
      }
      setNotifications([...notifications, notification]);
    });
  });*/

  useEffect(() => {
    getUserUnreadNotifications().then((response) => {
      const notifications = response.map((notification) => {
        return {
          id: notification.id,
          image: getIconByKey(notification.type),
          userId: notification.user.id,
          productId: notification.product.id,
          type: notification.type,
          message: notification.message,
          receivedTime: moment(notification.date).format("DD-MM-YYYY"),
        };
      });
      setNotifications(notifications);
    });
  }, []);

  const markNotification = (notificationId) => {
    setNotifications(notifications.filter(notification => notification.id !== notificationId));
    setNotificationAsReaded(notificationId);
  }

  const deleteNotification = (notificationId) => {
    setNotifications(notifications.filter(notification => notification.id !== notificationId));
    deleteUserNotification(notificationId);
  }

  return (
    <>
    <Notifications
      data={notifications}
      icon={notificationIcon}
      notificationCard={NotificationCard}
      checkFunction={markNotification}
      trashFunction={deleteNotification}
      header={{
        title: "Notifications",
        option: {
          text: "View All",
          onClick: () => viewAll(),
        },
      }}
      markAsRead={(data) => {
        console.log(data);
      }}
    />
    </>
  );
}

export default Notification;
