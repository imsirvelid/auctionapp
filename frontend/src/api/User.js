import axios from "axios";
import {BASE_URL} from "./Commons";

const AUTH_URL = BASE_URL + "/auth";
const URL = BASE_URL + "/user";

export const loginUser = async (request) => {
  const fetchData = await axios.post(AUTH_URL + "/login", request);
  return fetchData.data;
};

export const getCurrentUser = async () => {
  return axios.get(URL + "/getuser");
};

export const registerUser = async (request) => {
  const fetchData = await axios.post(AUTH_URL + "/register", request);
  return fetchData.data;
};

export const updateUserAddress = async(request) => {
  const fetchData = await axios.put(URL + "/address", request);
  return fetchData.data;
}
