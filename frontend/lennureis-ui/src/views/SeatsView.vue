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

</style>
