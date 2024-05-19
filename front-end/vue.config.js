const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    port: 8083, // Thay thế 3000 bằng cổng mà bạn muốn sử dụng
  },
};
