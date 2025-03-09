<script setup lang="ts">
import {useFlightStore} from "@/stores/flight.ts";
import {ref, onMounted} from "vue";
import {useRoute} from "vue-router";
import type { Flight } from "@/stores/flight.ts";

const route = useRoute();
const store = useFlightStore();

const flightId = route.query.flightId as string;
const passengers = route.query.passengers as string;

const flight = ref<Flight | null>(null);

onMounted(async () => {
  await store.getFlightById(flightId);
})
</script>

<template>
  <div v-if="flight" class="flight-details">
    <h3>Reservation Details</h3>
    <p>From - To: {{ flight.origin }} - {{ flight.destination }}</p>
    <p>Duration: {{ flight.departure }} - {{ flight.arrival }}</p>
    <p>Company: {{ flight.company }}</p>
    <p>Price: {{ flight.price }}</p>
    <p>Number of passengers: {{ passengers }} </p>
  </div>

</template>

<style scoped>

</style>
