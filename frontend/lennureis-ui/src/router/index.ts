import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/FlightsView.vue'
import FlightsView from "@/views/FlightsView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'flights',
      component: FlightsView,
    },
    {
      path: '/seats',
      name: 'seats',
      // route level code-splitting
      // this generates a separate chunk for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/SeatsView.vue'),
    },
  ],
})

export default router
