<template>
  <v-app-bar
    absolute
    color="white"
    elevate-on-scroll
    id="LogoHeader"
  >
    <v-spacer></v-spacer>
    <div>
      <v-btn text large to="/ko/home">
        KoDark Times
      </v-btn>
    </div>
    <v-spacer></v-spacer>
  </v-app-bar>
</template>

<script>
export default {

}
</script>

<style scoped>
#LogoHeader {
  position: fixed;
  border: 1px solid black;
}
</style>
