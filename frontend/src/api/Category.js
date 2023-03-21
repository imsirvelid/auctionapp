import axios from "axios";

import {BASE_URL} from "./Commons";

export const getParentCategories = async (page, sortBy = "created") => {
  const fetchData = await axios.get(BASE_URL + "/categories/parent");
  return fetchData.data;
};
