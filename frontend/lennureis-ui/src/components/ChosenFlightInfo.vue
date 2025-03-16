<script setup lang="ts">
import {onMounted, ref} from "vue";
import {type FlightStore, useFlightStore} from "@/stores/flightStore.ts";

const props = defineProps<{
  flightId: number
  passengerCount: number
  showSeats?: boolean
}>();

const store = useFlightStore();
const flight = ref<FlightStore | null>(null);

onMounted(async () => {
  flight.value = await store.getFlightById(props.flightId);
});
</script>

<template>
  <div v-if="flight" class="flight-details">
    <h3>Sinu lend:</h3>
    <p>Alguspunkt - Sihtkoht: {{ flight.origin }} - {{ flight.destination }}</p>
    <p>Kestvus: {{ flight.departureDate }} {{ flight.departureTime }}- {{ flight.arrivalDate }}
      {{ flight.arrivalTime }}</p>
    <p>Teenusepakkuja: {{ flight.company }}</p>
    <p>Hind: {{ flight.price }} €</p>
      <p v-if="passengerCount" > Reisjate arv: {{ passengerCount }}</p>
    <p v-if="showSeats !== false">Sinu istmed: {{ store.assignedSeats.join(' , ')}}</p>
  </div>
</template>

<style scoped>

</style>
