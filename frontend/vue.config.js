const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: 'http://3.34.40.128:8086/ ',
  },
  chainWebpack: (config) => {
    config.module.rules.delete('eslint')
  },
})
