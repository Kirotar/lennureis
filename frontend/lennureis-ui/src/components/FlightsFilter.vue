<script setup lang="ts">
import {useFlightStore} from "@/stores/flightStore.ts";
import {onMounted, ref} from "vue";

const store = useFlightStore();

const isVisible = ref<boolean>(false);

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

const resetFilters = () => {
  selectedOrigin.value = "";
  selectedDestination.value = "";
  selectedCompany.value = "";
  departureDate.value = "";
  arrivalDate.value = "";
  store.resetSearch();
};

onMounted(() => {
  store.getFlights()
})
</script>

<template>
  <div >
    <button @click="isVisible = !isVisible">Otsi lende</button>
    <form v-if="isVisible" @submit.prevent="handleSearch">
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

      <input type="submit" value="Otsi">
      <button type="button" @click="resetFilters">Tühjenda väljad</button>
    </form>
  </div>



  <div class="flights-container">
    <div v-if="store.filteredFlights.length === 0" class="no-flights-message">
    Ei leitud ühtegi lendu.  </div>

    <table  v-else class="filtered-flights-table">
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
      <tr v-for="flight in store.filteredFlights" :key="flight.id">
        <td class="table-data-cell">{{ flight.origin }}</td>
        <td class="table-data-cell">{{ flight.destination }}</td>
        <td class="table-data-cell">{{ flight.departure }}</td>
        <td class="table-data-cell">{{ flight.arrival }}</td>
        <td class="table-data-cell">{{ flight.price }} €</td>
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
