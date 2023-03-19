import axios from "axios";

import {BASE_URL} from "./Commons";

export const getLatestProducts = async (page) => {
  const fetchData = await axios.get(BASE_URL + "/products?page=" + page);
  return fetchData.data;
};

export const getRandomProduct = async () => {
  const data = await axios.get(BASE_URL + "/products/random");
  return data.data;
}