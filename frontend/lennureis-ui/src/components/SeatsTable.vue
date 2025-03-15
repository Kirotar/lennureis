<script setup lang="ts">
import {type GroupedRow, type Seats, useFlightStore} from "@/stores/flight.ts";
import {ref, onMounted, computed} from "vue";

const props = defineProps<{
  flightId: number
  passengerCount: number
}>();
const store = useFlightStore();

const groupedSeats = computed<GroupedRow[]>(() => {
  const grouped: Record<number, Record<string, Seats>> = {};

  store.seats.forEach((seat: Seats) => {
    if (!grouped[seat.seatRow]) {
      grouped[seat.seatRow] = {};
    }
    grouped[seat.seatRow][seat.seatColumn] = seat;
  });

  return Object.entries(grouped).map(([rowNumber, seats]) => ({
    rowNumber: Number(rowNumber),
    ...seats
  }));
});

onMounted(async () => {
  await store.getFlightById(props.flightId);
  await store.getSeats(props.flightId);
});
</script>

<template>
  <!--  <div v-if="flight" class="flight-details">
      <h3>Reservation Details</h3>
      <p>From - To: {{ flight.origin }} - {{ flight.destination }}</p>
      <p>Duration: {{ flight.departure }} - {{ flight.arrival }}</p>
      <p>Company: {{ flight.company }}</p>
      <p>Price: {{ flight.price }}</p>
      <p>Number of passengers: {{ passengers }} </p>
    </div>-->

  <p>Sinu istmed: {{store.assignedSeats}}</p>
  <p>Reisjate arv: {{passengerCount}}</p>
  <div class="plane-seats-container">
    <table>
      <thead>
      <tr>
        <th>A</th>
        <th>B</th>
        <th>C</th>
        <th></th>
        <th>D</th>
        <th>E</th>
        <th>F</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="row in groupedSeats" :key="row.rowNumber">
        <td v-for="letter in ['A', 'B', 'C']" :key="letter">
          <button v-if="letter">
            {{letter}} {{row.rowNumber}}
          </button>
        </td>
        <td> {{ row.rowNumber }}
        </td>
        <td v-for="letter in ['D', 'E', 'F']" :key="letter">
          <button v-if="letter">
            {{letter}} {{row.rowNumber}}
          </button>
        </td>
      </tr>
      </tbody>

    </table>

  </div>


</template>

<style scoped>

</style>
