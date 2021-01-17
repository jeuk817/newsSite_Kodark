export const utils = {
  methods: {
    // 앞글자만 대문자로만드는 함수
    toUpperCaseFirstChar(string) {
      return string.replace(/^[a-z]/, char => char.toUpperCase())
    },
    // 정해진 문자열 길이를 초과하면 자르고 "..." 붙이는 함수
    cutStringLength(string, limit) {
      return string.length > limit ? string.substring(0, limit) + '...' : string
    }
  },
  computed: {
    // 현재 라우트 반환 ex) signIn, signUp
    currentRoute () {
      const routeArr = this.$route.fullPath.split('/')
      return routeArr[routeArr.length - 1]
    }
  }
}
