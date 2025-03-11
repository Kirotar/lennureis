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

  <div class="flights-container">
    <table  class="filtered-flights-table">
      <thead class="table-header">
      <tr>
        <th class="table-header-cell">Alguspunkt</th>
        <th class="table-header-cell">Sihtkoht</th>
        <th class="table-header-cell">Väljumisaeg</th>
        <th class="table-header-cell">Saabumisaeg</th>
        <th class="table-header-cell">Hind</th>
        <th class="table-header-cell">Teenusepakkuja</th>
        <th class="table-header-cell"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="flight in store.flights" :key="flight.id">
        <td class="table-data-cell">{{ flight.origin }}</td>
        <td class="table-data-cell">{{ flight.destination }}</td>
        <td class="table-data-cell">{{ flight.departure }}</td>
        <td class="table-data-cell">{{ flight.arrival }}</td>
        <td class="table-data-cell">{{ flight.price }}</td>
        <td class="table-data-cell">{{ flight.company }}</td>
        <td class="table-data-cell" data-label="Action">

          <router-link
              :to="`/choices?flightId=${flight.id}`"
              class="make-booking-button"
          >
            Make a reservation
          </router-link>
        </td>      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>
