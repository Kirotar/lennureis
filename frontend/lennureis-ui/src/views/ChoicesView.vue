<script setup lang="ts">
import {ref} from "vue";
import {useRoute} from "vue-router";

const passengerCount = ref<string>("1");
const seatType = ref<string>("");

const route = useRoute();
const flightId = ref(route.query.flightId as string);
</script>

<template>
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
