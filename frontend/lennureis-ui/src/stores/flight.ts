import {ref} from 'vue'
import {defineStore} from 'pinia'
import {API_ENDPOINTS} from "@/config.ts";

export const useFlightStore = defineStore('flight', () => {
  const flights = ref([])

  async function getFlights() {
    const response = await fetch(API_ENDPOINTS.FLIGHT_INFO)
    flights.value = await response.json();
  }

  return(getFlights(), flights)
})
