<script setup lang="ts">
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {type Flight, useFlightStore} from "@/stores/flight.ts";

const store = useFlightStore();
const route = useRoute();

const flightId = Number(route.query.flightId);

const passengerCount = ref<string>("1");
const seatType = ref<string>("");

const flight = ref<Flight | null>(null);

onMounted(async () => {
  flight.value = await store.getFlightById(flightId);
});
</script>

<template>
  <div v-if="flight" class="flight-details">
    <h3>Sinu lend:</h3>
    <p>Alguspunkt - Sihtkoht: {{ flight.origin }} - {{ flight.destination }}</p>
    <p>Kestvus: {{ flight.departure }} - {{ flight.arrival }}</p>
    <p>Teenusepakkuja: {{ flight.company }}</p>
    <p>Hind: {{ flight.price }}</p>
  </div>

  <div class="seat-choices-container">
    <label for="passengers">Vali reisjate arv:</label>

    <select v-model="passengerCount" id="passengers">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>

    <label for="passengers">Vali istme tüüp:</label>

    <select v-model="seatType" id="seat-type">
      <option value="window">Akna ääres</option>
      <option value="middle">Keskel</option>
      <option value="aisle">Vahekäigu ääres</option>
    </select>

    <input type="checkbox" id="legroom" name="legroom" value="legroom">
    <label for="legroom"> Soovin rohkem jalaruumi</label><br>

    <input type="checkbox" id="exit" name="exit" value="exit">
    <label for="exit"> Soovin olla lähedal väljapääsule</label><br>


    <router-link
      :to="`/seats`"
      class="choose-seating-button"
    >
      Vaata istmekohti
    </router-link>

  </div>

</template>


<style scoped>

</style>
