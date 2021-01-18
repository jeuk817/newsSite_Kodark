<template>
  <v-app-bar
    absolute
    color="white"
    elevate-on-scroll
    height="40px"
    id="appBarArticleHeader"
  >
    <div class="appBarArticleHeaderContainer">
      <div>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
        <v-icon>search</v-icon>
        <span>Politics</span>
      </div>
      <div class="appBarArticleHeaderLogo">
        <router-link to="/en/home">
          <img src="../../assets/img/kodark.svg">
        </router-link>
      </div>
      <div>c</div>
    </div>
  </v-app-bar>
</template>

<script>
export default {
  
}
</script>

<style>
#appBarArticleHeader > div {
   padding: 0;
}
</style>

<style scoped>
#appBarArticleHeader {
  position: fixed;
}

.appBarArticleHeaderContainer{
  border: 1px solid red;
  width: 1200px;
  height: 100%;
  margin: 0 auto;
  padding: 0;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  align-content: center;
}

.appBarArticleHeaderLogo {
  display: grid;
  justify-items: center;
  align-items: center;
}

.appBarArticleHeaderLogo img {
  /* width: 300px; */
  height: 25px;
}
</style>
