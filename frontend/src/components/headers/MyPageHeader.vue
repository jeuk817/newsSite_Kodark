<template>
  <v-app-bar
    absolute
    color="white"
    dense
    id="MyPageHeader"
  >
    <!-- <div class="MyPageHeaderContent"> -->
      <div>
        <v-btn text large to="/ko/home">
          <span>K</span>
          <span>Home</span>
        </v-btn>
      </div>
      <div>
        <v-btn text large to="/ko/home">
          KoDark Times
        </v-btn>
      </div>
    <!-- </div> -->
  </v-app-bar>
</template>

<script>
export default {
  
}
</script>

<style scoped>
#MyPageHeader {
  position: fixed;
  width: 100%;
}

.MyPageHeaderContent {
  /* display: grid; */

}
</style>
