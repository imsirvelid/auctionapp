import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/auth";

export const loginUser = async (request) => {
  const fetchData = await axios.post(URL + "/login", request);
  return fetchData.data;
};

export const registerUser = async (request) => {
  const fetchData = await axios.post(URL + "/register", request);
  return fetchData.data;
};
