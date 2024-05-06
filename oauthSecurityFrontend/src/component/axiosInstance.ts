import axios from "axios"
export const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
    headers:{
        "Content-Type":"applicaslavtion/json",
        "Access-Control-Allow-Origin":"*"
    }
});
