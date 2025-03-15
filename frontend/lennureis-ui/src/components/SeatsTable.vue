<script setup lang="ts">
import {type GroupedRow, type Seats, useFlightStore} from "@/stores/flight.ts";
import {ref, onMounted, computed} from "vue";

const props = defineProps<{
  flightId: number
  passengerCount: number
}>();
const store = useFlightStore();

const selectedSeats = ref<string[]>(store.assignedSeats);

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

function isBooked(seat: any) {
  return seat && seat.booked === true;
}

function isSelected(seatRow: number, seatColumn: string) {
  const seatId = `${seatRow}${seatColumn}`;
  return selectedSeats.value.includes(seatId);
}

function selectSeat(seatRow: number, seatColumn: string) {
  const seatId = `${seatRow}${seatColumn}`;
  if(selectedSeats.value.length < props.passengerCount){
  return selectedSeats.value.push(seatId);
  } else {
    alert("Uue istme valimiseks tühista eelnev iste.")
  }
}

onMounted(async () => {
  await store.getFlightById(props.flightId);
  await store.getSeats(props.flightId);
});
</script>

<template>
  <p>Sinu istmed: {{ store.assignedSeats }}</p>
  <p>Reisjate arv: {{ passengerCount }}</p>
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
          <button v-if="letter"
                  :disabled="isBooked(row[letter])"
                  :class="{'booked': isBooked(row[letter]),
                                  'selected': isSelected(row.rowNumber, letter),
}"
                  @click="selectSeat(row.rowNumber, letter)">
            {{ row.rowNumber }}{{ letter }}
          </button>
        </td>
        <td> {{ row.rowNumber }}
        </td>
        <td v-for="letter in ['D', 'E', 'F']" :key="letter">
          <button v-if="letter"
                  :disabled="isBooked(row[letter])"
                  :class="{'booked': isBooked(row[letter]),
                                  'selected': isSelected(row.rowNumber, letter),
}"
                  @click="selectSeat(row.rowNumber, letter)">
            {{ row.rowNumber }}{{ letter }}
          </button>
        </td>
      </tr>
      </tbody>

    </table>

  </div>


</template>

<style scoped>
.selected {
  color: green;
}
</style>
