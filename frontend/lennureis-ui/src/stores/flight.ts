import {ref} from 'vue'
import {defineStore} from 'pinia'
import {API_ENDPOINTS} from "@/config.ts";

interface Flight {
  id: number;
  origin: string;
  destination: string;
  departure: string;
  arrival: string;
  price: number;
  company: string;
}

export const useFlightStore = defineStore('flight', () => {
  const flights = ref<Flight[]>([])

  async function getFlights() {
    const response = await fetch(API_ENDPOINTS.FLIGHT_INFO)
    flights.value = await response.json();
  }

  return{
    flights,
    getFlights
  }
})
