
const path = require('path')

module.exports = {
  outputDir : path.resolve(__dirname, '../backend/src/main/webapp'),
  devServer: {
    proxy: { // proxyTable 설정
        '': {
            target: 'http://localhost:8090/',
            changeOrigin: true ,
        }
    }
  },
  transpileDependencies: [
    'vuetify'
  ],
  lintOnSave : false
}
