const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
//Spring boot와 연계할 때 필요한 부분(static 밑으로 정적파일 넣어주기)
  outputDir : 
    '../spring_board_reply/src/main/resources/static',
  devServer: {
      proxy: 'http://localhost:8080'
    }
  



})
