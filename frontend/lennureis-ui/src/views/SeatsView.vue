<script setup lang="ts">
import SeatsTable from "@/components/SeatsTable.vue";
import {useRoute} from "vue-router";
import ChosenFlightInfo from "@/components/ChosenFlightInfo.vue";

const route = useRoute();

const flightId = Number(route.query.flightId);
const passengerCount = Number(route.query.passengerCount);
</script>

<template>
  <ChosenFlightInfo :flightId="flightId" :passengerCount="passengerCount" :showSeats="true"
  ></ChosenFlightInfo>

  <SeatsTable :flightId="flightId"
              :passengerCount="passengerCount"
  ></SeatsTable>

  <router-link
    :to="`/choices?flightId=${flightId}`"
    class="back-button"
  >
    Tagasi
  </router-link>
</template>

<style scoped>
.back-button {
  background-color: darkorange;
  color: white;
  border: none;
  padding: 10px 16px;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
}
.back-button:hover {
  background-color: orangered;
}

</style>
