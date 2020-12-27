<template>
  <div class="home">
    <HomeHeader />
    <HelloWorld />
    <HelloWorld />
    <Footer />
  </div>
</template>

<script>
import HomeHeader from '../components/headers/HomeHeader'
import Footer from '../components/footer/Footer'
import HelloWorld from '../components/HelloWorld'

export default {
  name: 'Home',
  components: {
    HomeHeader,
    HelloWorld,
    Footer
  }
}
</script>
