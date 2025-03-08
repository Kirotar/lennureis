<script setup lang="ts">
import {useFlightStore} from "@/stores/flight";
import {ref} from "vue";

const store = useFlightStore();

const selectedOrigin = ref<string | null>(null);
const selectedDestination = ref<string | null>(null);
const selectedCompany = ref<string | null>(null);
</script>

<template>
  <div>
    <form @submit.prevent="store.searchFlight">
      <select v-model="selectedOrigin" name="origin">
        <option v-for="flight in store.flights" :key="flight.id" :value="flight.origin">
          {{ flight.origin }}
        </option>
      </select>

      <select v-model="selectedDestination" name="destination">
        <option v-for="flight in store.flights" :key="flight.id" :value="flight.destination">
          {{ flight.destination }}
        </option>
      </select>

      <input type="date" id="departure" name="departure"><br><br>
      <input type="date" id="arrival" name="arrival"><br><br>

      <select v-model="selectedCompany" name="company">
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
