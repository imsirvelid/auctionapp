import axios from "axios";
import {BASE_URL, PAGE_SIZE} from "./Commons";

const URL = BASE_URL + "/products";

export const getLatestProducts = async (
  page,
  sortBy,
  orderBy,
  size = PAGE_SIZE
) => {
  const fetchData = await axios.get(URL, {
    params: {
      pageNumber: page,
      pageSize: size,
      sortField: sortBy,
      sortOrder: orderBy,
    },
  });
  return fetchData.data;
};

export const getRandomProduct = async () => {
  const data = await axios.get(URL + "/random");
  return data.data;
};

export const getProductById = async (id) => {
  const data = await axios.get(URL + "/" + id);
  return data.data;
};

export const searchProducts = async (
  page,
  sortBy,
  orderBy,
  size = PAGE_SIZE,
  name,
  category
) => {
  const fetchData = await axios.get(URL + "/search", {
    params: {
      pageNumber: page,
      pageSize: size,
      sortField: sortBy,
      sortOrder: orderBy,
      productName: name,
      categoryId: category,
    },
  });
  return fetchData.data;
};
