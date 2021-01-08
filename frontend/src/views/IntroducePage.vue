<template>
  <div>
      <LogoHeader />
      <IntroduceBody />
      <Footer />
  </div>
</template>

<script>
import LogoHeader from '../components/headers/LogoHeader'
import IntroduceBody from '../components/bodies/IntroduceBody'
import Footer from '../components/footer/Footer'
export default {
    components: {
        LogoHeader,
        IntroduceBody,
        Footer
    }
}
</script>

<style scoped>
   
</style>