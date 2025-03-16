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

//Tehisintellekti abi (formatDate):
const formatDate = (dateString: string): string => {
  if (!dateString) return "";
  const date = new Date(dateString);
  return date.toISOString().split('T')[0];
};

const handleSearch = () => {
  const formattedDepartureDate = formatDate(departureDate.value);
  const formattedArrivalDate = formatDate(arrivalDate.value);

  store.searchFlights(
    selectedOrigin.value,
    selectedDestination.value,
    formattedDepartureDate,
    formattedArrivalDate,
    selectedCompany.value
  );
};

const priceSort = ref("");
const sortByPrice = () => {
  if (priceSort.value === "highest-to-lowest") {
    store.filteredFlights.sort((a, b) => b.price - a.price);
  } else if (priceSort.value === "lowest-to-highest") {
    store.filteredFlights.sort((a, b) => a.price - b.price);
  }
}

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
  <div class="container">
    <div>
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
<br>
  <select v-model="priceSort" @change="sortByPrice">
    <option value="">Sorteeri hinna järgi</option>
    <option value="highest-to-lowest"> Kallimad enne</option>
    <option value="lowest-to-highest"> Odavamad enne</option>
  </select>

  <div class="flights-container">
    <div v-if="store.filteredFlights.length === 0" class="no-flights-message">
      Ei leitud ühtegi lendu.
    </div>

    <table v-else class="filtered-flights-table">
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
        <td class="table-data-cell">{{ flight.departureDate }} kell {{ flight.departureTime }}</td>
        <td class="table-data-cell">{{ flight.arrivalDate }} kell {{ flight.arrivalTime }}</td>
        <td class="table-data-cell">{{ flight.price }} €</td>
        <td class="table-data-cell">{{ flight.company }}</td>
        <td class="table-data-cell" data-label="Action">

          <router-link
            :to="`/choices?flightId=${flight.id}`"
            class="make-booking-button"
            @click="store.assignRandomSeats(flight.id)"
          >
            Make a reservation
          </router-link>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<style scoped>
.container {
  background: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  width: 90%;
  max-width: 1200px;
  margin: 1rem auto;
  padding: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

button {
  background-color: #32936f;
  color: white;
  border: none;
  padding: 10px 16px;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #267356;
}

form {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  margin-top: 20px;
  padding: 20px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.8);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

select, input[type="date"] {
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ddd;
  background-color: white;
  width: 200px;
  font-size: 14px;
}

input[type="submit"] {
  background-color: darkorange;
  color: white;
  border: none;
  padding: 10px 16px;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
}

input[type="submit"]:hover {
  background-color: orangered;
}

button[type="button"] {
  background-color: #f3f3f3;
  color: #333;
  border: 1px solid #ddd;
}

button[type="button"]:hover {
  background-color: #e5e5e5;
}

select[v-model="priceSort"] {
  margin: 1rem auto;
  display: block;
  padding: 8px 12px;
  border-radius: 6px;
  border: 1px solid #ddd;
  width: 200px;
  font-size: 14px;
}

.filtered-flights-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
}

.table-header {
  background-color: darkslategray;
  color: white;
}

.table-header-cell {
  padding: 12px 15px;
  text-align: left;
  font-weight: 600;
}

.table-data-cell {
  padding: 12px 15px;
  border-bottom: 1px solid #eee;
  color: darkslategray;
}

tbody tr:nth-child(even) {
  background-color: rgba(240, 240, 240, 0.5);
}

tbody tr:hover {
  background-color: rgba(211, 224, 255, 0.5);
}

.make-booking-button {
  background-color: #32936f;
  color: white;
  text-decoration: none;
  display: inline-block;
  padding: 8px 12px;
  border-radius: 6px;
  font-weight: 500;
  transition: background-color 0.3s;
}

.make-booking-button:hover {
  background-color: #267356;
}

.no-flights-message {
  padding: 20px;
  text-align: center;
  font-weight: 500;
  color: #666;
}

@media (max-width: 768px) {
  form {
    flex-direction: column;
  }

  select, input[type="date"] {
    width: 100%;
  }

  .table-header {
    display: none;
  }

  .filtered-flights-table, tbody, tr, td {
    display: block;
    width: 100%;
  }

  tr {
    margin-bottom: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    overflow: hidden;
  }

  .table-data-cell {
    display: flex;
    justify-content: space-between;
    text-align: right;
    padding: 10px 15px;
  }

  .table-data-cell::before {
    content: attr(data-label);
    font-weight: 600;
    text-align: left;
  }
}
</style>
