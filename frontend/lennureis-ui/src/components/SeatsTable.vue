<script setup lang="ts">
import {type Seats, useFlightStore} from "@/stores/flight.ts";
import {ref, onMounted} from "vue";
import type { Flight } from "@/stores/flight.ts";

const props = defineProps<{
  flightId: number
  passengerCount: number
}>();
const store = useFlightStore();

const flight = ref<Flight | null>(null);
const seats = ref<Seats | null>(null);


</script>

<template>
  <!--  <div v-if="flight" class="flight-details">
      <h3>Reservation Details</h3>
      <p>From - To: {{ flight.origin }} - {{ flight.destination }}</p>
      <p>Duration: {{ flight.departure }} - {{ flight.arrival }}</p>
      <p>Company: {{ flight.company }}</p>
      <p>Price: {{ flight.price }}</p>
      <p>Number of passengers: {{ passengers }} </p>
    </div>-->

  <div class="plane-seats-container">
    <table>
      <thead>
      <tr>
        <th>A</th>
        <th>B</th>
        <th>C</th>
        <th></th>
        <th>D</th>
        <th>E</th>
        <th>F</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="seat in store.seats" :key="seat.id">

      <td><button>O</button></td>
        <td><button>O</button></td>
        <td><button>O</button></td>
          <td>{{ seat.seatRow }}</td>
        <td><button>O</button></td>
        <td><button>O</button></td>
        <td><button>O</button></td>
      </tr>
      </tbody>/
    </table>

  </div>


</template>

<style scoped>

</style>
