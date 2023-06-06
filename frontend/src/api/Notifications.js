import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/notifications";

export const getUserUnreadNotifications = async () => {
  const fetchData = await axios.get(URL + "/unread");
  return fetchData.data;
}

export const getUserAllNotifications = async () => {
  const fetchData = await axios.get(URL + "/all");
  return fetchData.data;
}

export const setNotificationAsReaded = async (id) => {
  const fetchData = await axios.post(URL + "/read/" + id);
  return fetchData.data;
}