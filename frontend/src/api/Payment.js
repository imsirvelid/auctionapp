import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/payment";


export const getPaymentIntent = async (request) => {
  const fetchData = await axios.post(URL + "/create-payment-intent", request, {
    headers: {
      "Content-Type": "application/json"
    }
  });
  return fetchData.data;
}