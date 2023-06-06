import { getUserAllNotifications } from 'api/Notifications';
import NotificationCard from 'components/notification-card/NotificationCard';
import infoIcon from "assets/img/icons8-info-144.png";
import successIcon from "assets/img/icons8-success-144.png";
import moment from "moment";
import React, { useState, useEffect } from 'react'


const images = [
  {key: "INFO", image: {infoIcon}},
  {key: "SUCCESS", image: {successIcon}},
];

function Notifications() {

  const getIconByKey = (key) => {
    console.log(infoIcon);
    const icon = images.find((item) => item.key === key);
    if (key === "INFO")
      return icon.image.infoIcon;
    return icon.image.successIcon;
  };

  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    getUserAllNotifications().then((response) => {
      const notifications = response.map((notification) => {
        return {
          id: notification.id,
          image: getIconByKey(notification.type),
          message: notification.message,
          receivedTime: moment(notification.date).format("DD-MM-YYYY"),
        };
      });
      setNotifications(notifications);
    });
  }, []);

  return (
    <div className='container'>
      { notifications.map(notification => (
          <NotificationCard data={notification} />
      ))}
    </div>
  )
}

export default Notifications