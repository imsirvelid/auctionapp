import axios from "axios";

axios.interceptors.request.use(function (config) {
  const token = localStorage.getItem("token");
  config.headers["Content-Type"] = "application/json";
  if (token)
    config.headers.Authorization = "Bearer " + token;
  return config;
});
