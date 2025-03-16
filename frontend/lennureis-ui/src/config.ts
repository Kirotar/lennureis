export const API_BASE_URL = 'http://localhost:8080/flight';

export const API_ENDPOINTS = {
  FLIGHT_INFO: `${API_BASE_URL}/flights`,
  FLIGHT_SEARCH: `${API_BASE_URL}/search`,
  FLIGHT_SEARCH_ID: `${API_BASE_URL}/search_by_id`,
  FLIGHT_ASSIGNED_SEATS: `${API_BASE_URL}/assign-seats`,
  FLIGHT_GET_SEATS: `${API_BASE_URL}/get-all-seats`,
  FLIGHT_ASSIGN_TAKEN_SEATS: `${API_BASE_URL}/assign-taken-seats`,
};
