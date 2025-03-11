<script setup lang="ts">
import {ref} from "vue";

const passengerCount = ref<string>("1");

</script>

<template>
  <label for="passengers">Vali reisjate arv:</label>

  <select v-model="passengerCount" id="passengers">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
  </select>
</template>

:to="`/choices?flightId=${flight.id}&passengers=${passengerCount}`"
class="make-booking-button"
<style scoped>

</style>
