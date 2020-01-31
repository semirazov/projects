import axios from "axios";
import { getAccessToken } from "@/utils/auth";

const BASE_URL = "http://localhost:3333";

function getPublicBattles() {
  const url = `${BASE_URL}/api/battles/public`;
  return axios.get(url).then(response => response.data);
}

function getPrivateBattles() {
  const url = `${BASE_URL}/api/battles/private`;
  return axios
    .get(url, { headers: { Authorization: `Bearer ${getAccessToken()}` } })
    .then(response => response.data);
}

export { getPublicBattles, getPrivateBattles };
