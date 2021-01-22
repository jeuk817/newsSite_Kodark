<template>
<div class="errorPage">
  <!-- <AppBarHeader /> -->
   <MyPageHeader/>
  <img src="../assets/img/donut404.png" alt="">
    <span class="material-icons homeContainer"><router-link class="homeBtn" to="/en/home">home</router-link> </span>

</div>

  
</template>

<script>
import AppBarHeader from '../components/headers/AppBarHeader'
import MyPageHeader from '../components/headers/MyPageHeader'
export default {
  data: () => ({
    sections: [{ rel: 'Politics', href: '/en/section/politics' }, { rel: 'Economy', href: '/en/section/economy' }, { rel: 'Society', href: '/en/section/society' }, { rel: 'Tech', href: '/en/section/tech' }, { rel: 'World', href: '/en/section/world' }, { rel: 'Sports', href: '/en/section/sports' }, { rel: 'Weather', href: '/en/section/weather' }],
    isSignedIn: false
  }),
    components: {
    AppBarHeader,
    MyPageHeader
  },
}
</script>

<style scoped>
.errorPage img{
  margin-top: 100px;
}
.errorPage span{
}
.homeContainer{
  margin-bottom: 200px;
  margin-right: 40px;
  display: flex;
  justify-content: center;
}
.errorPage .homeBtn{
  font-size: 100px;
  color: #bee5d3;
}
</style>
