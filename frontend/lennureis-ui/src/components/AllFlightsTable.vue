<script setup lang="ts">
import {useFlightStore} from "@/stores/flight";

const store = useFlightStore();
store.getFlights()
</script>

<template>
  <div v-for="flight in store.flights" :key="flight.id" class="flights-container">
    <button  class="all-flights-button">Vaata kõiki lende!</button>
    <table class="all-flights-table">
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
      <tr>
        <td class="table-data-cell">{{ flight.origin }}</td>
        <td class="table-data-cell">{{ flight.destination }}</td>
        <td class="table-data-cell">{{flight.departure}}</td>
        <td class="table-data-cell">{{flight.arrival}}</td>
        <td class="table-data-cell">{{flight.price}}</td>
        <td class="table-data-cell">{{flight.company}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>
