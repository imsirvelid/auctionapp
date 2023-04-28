import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/auth";

export const loginUser = async (request) => {
  const fetchData = await axios.post(URL + "/login", request);
  return fetchData.data;
};

export const getCurrentUser = async () => {
  return axios.get(URL + "/getuser", {
    headers: {Authorization: "Bearer " + localStorage.getItem("token")},
  });
};

export const registerUser = async (request) => {
  const fetchData = await axios.post(URL + "/register", request);
  return fetchData.data;
};
