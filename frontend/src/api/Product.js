import axios from "axios";

import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/products";

export const getLatestProducts = async (page, sortBy, orderBy) => {
  const fetchData = await axios.get(URL, { params: { page: page, sort: sortBy, order: orderBy }});
  return fetchData.data;
};

export const getRandomProduct = async () => {
  const data = await axios.get(URL + "/random");
  return data.data;
}
