import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useFlightStore = defineStore('flight', () => {
  const flights = ref([])

})
