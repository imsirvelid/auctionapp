import axios from "axios";

import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/bids";

export const getProductBidInfo = async (productId) => {
  const fetchData = await axios.get(URL + "/info/" + productId);
  return fetchData.data;
};

export const getBidsForUser = async () => {
  const fetchData = await axios.get(URL + "/user", {
    headers: {Authorization: "Bearer " + localStorage.getItem("token")},
  });
  return fetchData.data;
};
