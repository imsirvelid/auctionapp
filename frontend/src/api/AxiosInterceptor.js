import axios from "axios";

axios.interceptors.request.use(function (config) {
  if (token)
    config.headers.Authorization = "Bearer " + localStorage.getItem("token");
  return config;
});
