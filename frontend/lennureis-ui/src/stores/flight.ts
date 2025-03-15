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

export interface Seats {
  id: number;
  flightId: number;
  seatColumn: string;
  seatRow: number;
  legroom: boolean;
  seatType: string;
  exitRow: boolean;
  booked: boolean;
}

export interface GroupedRow {
  rowNumber: number;
  [column: string]: Seats | number;
}

export const useFlightStore = defineStore('flight', () => {
  const flights = ref<Flight[]>([])
  const seats = ref<Seats[]>([])
  const assignedSeats = ref<string[]>([])
  const groupedRow = ref<GroupedRow[]>([])

  async function getFlights() {
    const response = await fetch(API_ENDPOINTS.FLIGHT_INFO)
    flights.value = await response.json();
  }

  async function searchFlights(origin: string,
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

  async function getSeats(id: number) {
    const response = await fetch(`${API_ENDPOINTS.FLIGHT_GET_SEATS}/${id}`);
    const data = await response.json();
    seats.value = data;
  }

  async function getAssignedSeats(flightId: number, legroom: boolean, seatType: string, exitRow: boolean, nrOfPassengers: number) {
    const url = new URL(API_ENDPOINTS.FLIGHT_ASSIGNED_SEATS, window.location.origin);
    url.searchParams.append('flightId', flightId.toString());
    if (legroom) url.searchParams.append('legroom', legroom.toString());
    if (seatType) url.searchParams.append('seatType', seatType.toString());
    if (exitRow) url.searchParams.append('exitRow', exitRow.toString());
    url.searchParams.append('nrOfPassengers', nrOfPassengers.toString());

    const response = await fetch(url.toString());
    assignedSeats.value = await response.json();
    return assignedSeats.value;
  }

  return {
    flights,
    seats,
    assignedSeats,
    groupedRow,
    getFlights,
    searchFlights,
    getFlightById,
    getAssignedSeats,
    getSeats,
  }
})
