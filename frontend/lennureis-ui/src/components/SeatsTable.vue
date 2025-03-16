<script setup lang="ts">
import {type GroupedRow, type Seats, useFlightStore} from "@/stores/flightStore.ts";
import {onMounted, computed} from "vue";

const props = defineProps<{
  flightId: number
  passengerCount: number
}>();
const store = useFlightStore();

const selectedSeats = computed<string[]>(() => store.assignedSeats);

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
  const alreadySelected = store.assignedSeats.indexOf(seatId);

  if (alreadySelected !== -1) {
    return store.assignedSeats.splice(alreadySelected, 1)
  } else {
    if (store.assignedSeats.length < props.passengerCount) {
      return store.assignedSeats.push(seatId);
    } else {
      alert("Uue istme valimiseks tühista eelnev iste.")
    }
  }
}

onMounted(async () => {
  await store.getFlightById(props.flightId);
  await store.getSeats(props.flightId);
});
</script>

<template>
  <div class="plane-seats-container">
    <div class="plane-container">
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
    </div>
  <div class="confirmation-button-container">
    <router-link
      :to="`/confirmation?flightId=${props.flightId}&passengerCount=${passengerCount}`"
      class="confirm-booking-button"
    >
      Kinnita istmekohad
    </router-link>
  </div>
</template>

<style scoped>
.plane-container {
  background: dimgrey;
  border-radius: 20px;
  backdrop-filter: blur(10px);
  margin: 1rem auto;
  padding: 1.5rem;
  width: fit-content;
  color: white;
}

.selected {
  background-color: darkseagreen;
}

button {
  width: 40px;
  height: 40px;
  color: darkslategray;
}

.confirm-booking-button {
  background-color: #32936f;
  color: white;
  text-decoration: none;
  display: inline-block;
  padding: 8px 12px;
  border-radius: 6px;
  font-weight: 500;
  transition: background-color 0.3s;
}

.confirm-booking-button:hover {
  background-color: #267356;
}

.confirmation-button-container{
  justify-content: center;
display: flex;
}

</style>
