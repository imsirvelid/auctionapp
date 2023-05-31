import axios from "axios";
import {BASE_URL} from "./Commons";

const URL = BASE_URL + "/country";

export const getAllCountries = async () => {
  const fetchData = await axios.get(URL);
  const countries = fetchData.data.geonames.map((country) => ({
    code: country.countryCode,
    name: country.countryName,
  }));
  return countries;
};

export const getAllCitiesForCountry = async (countryCode) => {
  const fetchData = await axios.get(URL + "/" + countryCode);
  const cities = fetchData.data.geonames.map((city) => {
    return city.toponymName;
  });
  return cities;
};
