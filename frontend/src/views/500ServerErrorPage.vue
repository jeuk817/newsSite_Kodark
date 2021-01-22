<template>
<div class="errorPage">
  <!-- <AppBarHeader /> -->
   <MyPageHeader/>
    
    <router-link class="homeBtn" to="/en/home">
   <v-btn
      fab
      dark
      large
      color="cyan"
      class="homeBtn"
    >
     HOMEPAGE 
    </v-btn>
</router-link> 
</div>

  
</template>

<script>
import AppBarHeader from '../components/headers/AppBarHeader'
import MyPageHeader from '../components/headers/MyPageHeader'
export default {
  data: () => ({
    // sections: [{ rel: 'Politics', href: '/en/section/politics' }, { rel: 'Economy', href: '/en/section/economy' }, { rel: 'Society', href: '/en/section/society' }, { rel: 'Tech', href: '/en/section/tech' }, { rel: 'World', href: '/en/section/world' }, { rel: 'Sports', href: '/en/section/sports' }, { rel: 'Weather', href: '/en/section/weather' }],
    // isSignedIn: false
  }),
    components: {
    AppBarHeader,
    MyPageHeader
  },
}
</script>

<style scoped>
.errorPage{
  margin-top: 100px;
  padding: 550px 0 30px;
  background: url('../assets/img/500erro_page.png');
  background-size: 100% 700px;
  background-repeat: no-repeat;
  background-position: center;
  
}
.homeBtn{
  margin: 0px 0px 200px 100px;
  width: 100px;
  height: 100px;
}

</style>
