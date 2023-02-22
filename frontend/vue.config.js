const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: "http://localhost:8086",
  },
  chainWebpack: (config) => {
    config.module.rules.delete("eslint");
  },
});
