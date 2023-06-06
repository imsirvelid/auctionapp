import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/creditcard";

export const getUserCreditCardInfo = async() => {
  const fetchData = await axios.get(URL + "/user");
  return fetchData.data;
}

export const updateOrCreateCreditCardInfo = async(request) => {
  const fetchData = await axios.post(URL + "/create", request);
  return fetchData.data;
}
