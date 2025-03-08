<script setup lang="ts">
import {useFlightStore} from "@/stores/flight";
import {ref} from "vue";

const store = useFlightStore();

const selectedOrigin = ref<string>("");
const selectedDestination = ref<string>("");
const selectedCompany = ref<string>("");
const departureDate = ref<string>("");
const arrivalDate = ref<string>("");

const handleSearch = () => {
  store.searchFlights(
      selectedOrigin.value,
      selectedDestination.value,
      departureDate.value,
      arrivalDate.value,
      selectedCompany.value
  );
};
</script>

<template>
  <div>
    <form @submit.prevent="handleSearch">
      <select v-model="selectedOrigin" name="origin">
        <option value="">Select Origin</option>
        <option v-for="flight in store.flights" :key="flight.id" :value="flight.origin">
          {{ flight.origin }}
        </option>
      </select>

      <select v-model="selectedDestination" name="destination">
        <option value="">Select Destination</option>
        <option v-for="flight in store.flights" :key="flight.id" :value="flight.destination">
          {{ flight.destination }}
        </option>
      </select>

      <input v-model="departureDate" type="date" id="departure" name="departure"><br><br>
      <input v-model="arrivalDate" type="date" id="arrival" name="arrival"><br><br>

      <select v-model="selectedCompany" name="company">
        <option value="">Select Company</option>
        <option v-for="flight in store.flights" :key="flight.id" :value="flight.company">
          {{ flight.company }}
        </option>
      </select>

      <input type="submit" value="Submit">
    </form>
  </div>
</template>

<style scoped>

</style>
