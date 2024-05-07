import axios from "axios"
// import Cookies from "js-cookie";

const axiosInstance = axios.create({
    baseURL:"http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin":"*"
    }
});
export  default  axiosInstance;