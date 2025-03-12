<script setup lang="ts">
/*import {useFlightStore} from "@/stores/flight.ts";
import {ref, onMounted} from "vue";
import {useRoute} from "vue-router";
import type { Flight } from "@/stores/flight.ts";

const route = useRoute();
const store = useFlightStore();

const flightId = route.query.flightId as string;
const passengers = route.query.passengers as string;

const flight = ref<Flight | null>(null);*/


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
      <tr>
        <td><button>O</button></td>
        <td><button>O</button></td>
        <td><button>O</button></td>
        <td>1</td>
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
