import axios from "axios";

import {BASE_URL, pageSize} from "./Commons";

const URL = BASE_URL + "/products";

export const getLatestProducts = async (page, sortBy, orderBy, size = pageSize) => {
  const fetchData = await axios.get(URL, { params: { pageNumber: page, pageSize: size, sortField: sortBy, sortOrder: orderBy }});
  return fetchData.data;
};

export const getRandomProduct = async () => {
  const data = await axios.get(URL + "/random");
  return data.data;
}
