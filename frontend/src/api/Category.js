import axios from "axios";

import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/categories"

export const getParentCategories = async () => {
  const fetchData = await axios.get(URL + "/parent");
  return fetchData.data;
};
