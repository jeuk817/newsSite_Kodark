export const dateUtils = {
  method: {
    // 현재 혹은 매개변수로 들어온 date를 formatting하는 함수
    getYMDWHNS (_date) {
      const weeks = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
      const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
      const date = _date || new Date()

      const year = date.getFullYear()
      const month = months[date.getMonth()]
      const day = this.makeTimeToTwoDigits(date.getDate())
      const week = weeks[date.getDay()]
      const hours = this.makeTimeToTwoDigits(date.getHours())
      const minutes = this.makeTimeToTwoDigits(date.getMinutes())
      const seconds = this.makeTimeToTwoDigits(date.getSeconds())

      return { year, month, day, week, hours, minutes, seconds }
    },

    // 시간을 mm, dd, hh, mi 등 두자리로 만들기위해 10보다 작으면 앞에 '0'을 붙여주는 함수
    makeTimeToTwoDigits (time) {
      return time < 10 ? '0' + time : time
    }
  }
}
