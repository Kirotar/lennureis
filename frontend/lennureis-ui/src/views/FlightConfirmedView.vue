<script setup lang="ts">
import ChosenFlightInfo from "@/components/ChosenFlightInfo.vue";
import {useRoute} from "vue-router";

const route = useRoute();

const flightId = Number(route.query.flightId);
const passengerCount = Number(route.query.passengerCount);
</script>

<template>
  <div class="header-container"><h2>Sinu broneering on kinnitatud!</h2></div>

  <ChosenFlightInfo :flightId="flightId" :passengerCount="passengerCount" :showSeats="true"
  ></ChosenFlightInfo>
</template>

<style scoped>
.header-container{
  justify-content: center;
  display: flex;
  color: darkslategray;
}
</style>
