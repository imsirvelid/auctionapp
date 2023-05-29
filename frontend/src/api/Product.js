import axios from "axios";
import {BASE_URL, PAGE_SIZE} from "./Commons";
import { storage } from "config/firebase";
import { ref, uploadBytes, getDownloadURL } from "firebase/storage";

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

export const getActiveUserProducts = async () => {
  const fetchData = await axios.get(URL + "/user/active");
  return fetchData.data;
};

export const getSoldUserProducts = async () => {
  const fetchData = await axios.get(URL + "/user/sold");
  return fetchData.data;
};

export const createProduct = async (request) => {
  const fetchData = await axios.post(URL + "/create", request);
  return fetchData.data;
};

export const uploadProductImages = async (files) => {
  return uploadImages(files);
}

async function uploadImage(image) {
  const storageRef = ref(storage, `/products/${Date.now()}-${image.name}`);

  const response = await uploadBytes(storageRef, image);
  const url = await getDownloadURL(response.ref);
  return url;
}

export default async function uploadImages(images) {
  const imagePromises = Array.from(images, (image) => uploadImage(image));

  const imageRes = await Promise.all(imagePromises);
  return imageRes;
}

export const setPurchasedProduct = async (productId) => {
  const fetchData = await axios.post(URL + "/pay/" + productId);
  return fetchData.data;
};
