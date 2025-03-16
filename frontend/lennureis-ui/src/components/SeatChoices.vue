<script setup lang="ts">
import {onMounted, ref} from "vue";
import {type FlightStore, useFlightStore} from "@/stores/flightStore.ts";

const props = defineProps<{
  flightId: number
}>();

const store = useFlightStore();

const passengerCount = ref<string>("1");
const seatType = ref<string>("");
const legroom = ref<boolean>(false);
const exitRow = ref<boolean>(false);

const flight = ref<FlightStore | null>(null);

onMounted(async () => {
  flight.value = await store.getFlightById(props.flightId);
});
</script>

<template>
  <div class="seat-choices-container">
    <label for="passengers">Vali reisjate arv:</label>
    <select v-model="passengerCount" id="passengers" class="form-select">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>

    <label for="seat-type">Vali istme tüüp:</label>
    <select v-model="seatType" id="seat-type" class="form-select">
      <option value="window">Akna ääres</option>
      <option value="middle">Keskel</option>
      <option value="aisle">Vahekäigu ääres</option>
    </select>

    <div class="form-checkbox">
      <input v-model="legroom" type="checkbox" id="legroom" name="legroom" value="legroom">
      <label for="legroom"> Soovin rohkem jalaruumi</label>
    </div>

    <div class="form-checkbox">
      <input v-model="exitRow" type="checkbox" id="exitRow" name="exitRow" value="exitRow">
      <label for="exitRow"> Soovin olla lähedal väljapääsule</label>
    </div>

    <router-link
        :to="`/seats?flightId=${props.flightId}&passengerCount=${passengerCount}`"
        class="choose-seating-button"
        @click="store.getAssignedSeats(props.flightId, legroom, seatType, exitRow, parseInt(passengerCount))"
    >
      Vaata istmekohti
    </router-link>

  </div>
</template>

<style scoped>
.seat-choices-container {
  background: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  width: 90%;
  max-width: 1200px;
  margin: 1rem auto;
  padding: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.form-select {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 6px;
  background-color: #f9f9f9;
  font-size: 1rem;
  transition: border-color 0.3s;
}

.form-select:focus {
  border-color: #32936f;
  outline: none;
}

.form-checkbox {
  margin-bottom: 0.75rem;
  display: flex;
  align-items: center;
}

.form-checkbox input[type="checkbox"] {
  margin-right: 0.5rem;
  width: 16px;
  height: 16px;
  accent-color: #32936f;
}

.choose-seating-button {
  background-color: #32936f;
  color: white;
  text-decoration: none;
  display: inline-block;
  padding: 8px 12px;
  border-radius: 6px;
  font-weight: 500;
  transition: background-color 0.3s;
}

.choose-seating-button:hover {
  background-color: #267356;
}
</style>
