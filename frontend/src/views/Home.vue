<template>
  <div class="home">
    <HomeHeader />
    <HelloWorld />
    <HelloWorld />
  </div>
</template>

<script>
import HomeHeader from '../components/headers/HomeHeader'
import HelloWorld from '../components/HelloWorld'

export default {
  name: 'Home',
  components: {
    HomeHeader,
    HelloWorld,
  }
}
</script>
