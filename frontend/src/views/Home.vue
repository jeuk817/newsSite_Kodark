<template>
  <div class="home">
    <HomeHeader />
    <PopularNews1 />
    <PopularNewsCarousels />
    <!-- <HelloWorld />
    <HelloWorld />
    <layout2 />
    <layout1 /> -->
  </div>
</template>

<script>
import HomeHeader from '../components/headers/HomeHeader'
import HelloWorld from '../components/HelloWorld'
import layout1 from '../components/test/layout1'
import layout2 from '../components/test/layout2'
import Footer from '../components/footer/Footer'
import PopularNews1 from '../components/bodies/article/PopularNews1'
import PopularNewsCarousels from '../components/bodies/article/PopularNewsCarousels'

export default {
  name: 'Home',
  components: {
    HomeHeader,
    HelloWorld,
    layout1,
    layout2,
    Footer,
    PopularNews1,
    PopularNewsCarousels
  }
}
</script>
