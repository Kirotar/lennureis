import {ref} from 'vue'
import {defineStore} from 'pinia'
import {API_ENDPOINTS} from "@/config.ts";

export interface Flight {
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

  async function searchFlights( origin: string,
                                destination: string,
                                departure: string,
                                arrival: string,
                                company: string) {

    const url = new URL(API_ENDPOINTS.FLIGHT_SEARCH, window.location.origin);

    if (origin) url.searchParams.append('origin', origin);
    if (destination) url.searchParams.append('destination', destination);
    if (departure) url.searchParams.append('departure', departure);
    if (arrival) url.searchParams.append('arrival', arrival);
    if (company) url.searchParams.append('company', company);

    const response = await fetch(url.toString());
    flights.value = await response.json();
  }


  //AI used to add error handling
  async function getFlightById(id: number): Promise<Flight | null> {
    try {
      const response = await fetch(`${API_ENDPOINTS.FLIGHT_SEARCH_ID}/${id}`);
      if (!response.ok) {
        console.error('Failed to fetch flight. Status:', response.status);
        return null;
      }
      return await response.json() as Flight;
    } catch (error) {
      console.error('Error fetching flight:', error);
      return null;
    }
  }


  return{
    flights,
    getFlights,
    searchFlights,
    getFlightById,
  }
})
