import axios from "axios";

import {BASE_URL} from "./Commons";

export const getLatestProducts = async (page, sortBy = "created") => {
  const fetchData = await axios.get(BASE_URL + "/products?page=" + page + "&sort=" + sortBy);
  return fetchData.data;
};

export const getRandomProduct = async () => {
  const data = await axios.get(BASE_URL + "/products/random");
  return data.data;
}

export const getProductById = async(id) => {
  const data = await axios.get(BASE_URL + "/product/" + id)
  return data.data;
}