<script setup lang="ts">

import FlightsFilter from "@/components/FlightsFilter.vue";
</script>

<template>
  <main>
    <div class="header-container"><h2>Leia omale sobiv reis:</h2>
    </div>
    <FlightsFilter/>
  </main>
</template>

<style scoped>
.header-container{
  justify-content: center;
  display: flex;

}
</style>
