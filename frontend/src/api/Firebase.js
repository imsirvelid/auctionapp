import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/firebase";


export const getFirebaseConfig = async () => {
  const fetchData = await axios.get(URL);
  return fetchData.data;
}