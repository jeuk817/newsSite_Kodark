<template>
  <v-app-bar
  absolute
  color="white"
  inverted-scroll
  scroll-threshold=95
  id="homeHeaderNavi"
  height=40px
  >
    <div class="homeHeaderBar">
      <div class="homeHeaderNavi">
        <ul class="homeHeaderNaviChild2">
          <li>
            <v-btn icon color="black">
              <v-icon>home</v-icon>
            </v-btn>
          </li>
          <li>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </li>
          <li v-for="(section, i) in sections" :key="i">
            <v-btn text small color="black" :to="section.href">
              {{section.rel}}
            </v-btn>
          </li>
        </ul>
      </div>
    </div>
  </v-app-bar>
</template>

<script>
export default {
  data: () => ({
    sections: [{ rel: 'Politics', href: '/en/section/politics' }, { rel: 'Economy', href: '/en/section/economy' }, { rel: 'Society', href: '/en/section/society' }, { rel: 'Tech', href: '/en/section/tech' }, { rel: 'World', href: '/en/section/world' }, { rel: 'Sports', href: '/en/section/sports' }, { rel: 'Weather', href: '/en/section/weather' }],
    isSignedIn: false
  }),
}
</script>

<style scoped>
#homeHeaderNavi {
  position: fixed;
}

.homeHeaderNavi {
  padding: 6px 60px;
}

.homeHeaderBar {
  width: 1200px;
  margin: 0 auto;
  padding: 0;
}

.homeHeaderNaviChild2 {
  list-style: none;
  display: grid;
  grid-template-columns: repeat(9, 1fr);
  justify-items: center;
  align-items: center;
}
</style>
