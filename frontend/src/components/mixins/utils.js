export const utils = {
  computed: {
    // 현재 라우트 반환 signIn, signUp
    currentRoute () {
      const routeArr = this.$route.fullPath.split('/')
      return routeArr[routeArr.length - 1]
    }
  }
}
