<script setup lang="ts">
import {useFlightStore} from "@/stores/flight";
import {onMounted, ref} from "vue";

const flightsTableIsVisible = ref(false);

const store = useFlightStore();

onMounted(() => {
  store.getFlights()
})
</script>

<template>
  <div class="flights-container">
    <button @click="flightsTableIsVisible=!flightsTableIsVisible" class="all-flights-button">Vaata kõiki lende!</button>
    <table v-if="flightsTableIsVisible" class="all-flights-table">
      <thead class="table-header">
      <tr>
        <th class="table-header-cell">Alguspunkt</th>
        <th class="table-header-cell">Sihtkoht</th>
        <th class="table-header-cell">Väljumisaeg</th>
        <th class="table-header-cell">Saabumisaeg</th>
        <th class="table-header-cell">Hind</th>
        <th class="table-header-cell">Teenusepakkuja</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="flight in store.flights" :key="flight.id">
        <td class="table-data-cell">{{ flight.origin }}</td>
        <td class="table-data-cell">{{ flight.destination }}</td>
        <td class="table-data-cell">{{ flight.departure }}</td>
        <td class="table-data-cell">{{ flight.arrival }}</td>
        <td class="table-data-cell">{{ flight.price }}</td>
        <td class="table-data-cell">{{ flight.company }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>
