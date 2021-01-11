<template>
  <div class="home">
    <HomeHeader />
    <HelloWorld />
    <!-- <layout1 /> -->
    <layout2 />
    <Footer />
    <HelloWorld />
  </div>
</template>

<script>
import HomeHeader from '../components/headers/HomeHeader'
import HelloWorld from '../components/HelloWorld'
import layout1 from '../components/test/layout1'
import layout2 from '../components/test/layout1'

export default {
  name: 'Home',
  components: {
    HomeHeader,
    HelloWorld,
    layout1,
    layout2,
    Footer
  }
}
</script>
