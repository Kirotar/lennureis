<script setup lang="ts">
import SeatsTable from "@/components/SeatsTable.vue";
import {useRoute} from "vue-router";
const route = useRoute();

const flightId = Number(route.query.flightId);
const passengerCount = Number(route.query.passengerCount);
</script>

<template>
<SeatsTable  :flightId="flightId"
             :passengerCount="passengerCount"
></SeatsTable>
</template>

<style scoped>

</style>
