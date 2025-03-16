<script setup lang="ts">
import {useRoute} from "vue-router";
import SeatChoices from "@/components/SeatChoices.vue";
import ChosenFlightInfo from "@/components/ChosenFlightInfo.vue";
const route = useRoute();

const flightId = Number(route.query.flightId);
</script>

<template>
  <ChosenFlightInfo :flightId="flightId" :passengerCount="0" :showSeats="false"></ChosenFlightInfo>
  <SeatChoices :flightId="flightId"></SeatChoices>
</template>


<style scoped>

</style>
