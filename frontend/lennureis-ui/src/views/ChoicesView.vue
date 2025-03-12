<script setup lang="ts">
import {useRoute} from "vue-router";
import SeatChoices from "@/components/SeatChoices.vue";
const route = useRoute();

const flightId = Number(route.query.flightId);
</script>

<template>
  <SeatChoices :flightId="flightId"></SeatChoices>
</template>


<style scoped>

</style>
