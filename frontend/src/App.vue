<template>
  <v-app>
    <router-view />
    <!-- <Home /> -->
  </v-app>
</template>

<script>
// import Home from './views/Home'

export default {
  name: 'App',

  components: {
    // Home
  },

  data: () => ({
    //
  })
}
</script>

<style scoped>
#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 3%;
}
</style>
