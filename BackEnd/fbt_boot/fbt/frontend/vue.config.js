const path = require("path");

module.exports = {
  transpileDependencies: ["vuetify"],
  configureWebpack: {
    resolve: {
      alias: {
        // '@'는 현재 프로젝트의 client/src/까지의 최종 경로를 의미합니다.
        "@": path.join(__dirname, "src/")
      }
    }
  },
  outputDir: "../src/main/resources/static",  
  indexPath: "../static/index.html",
  devServer: {
    historyApiFallback: true,
  },
  // publicPath: "/fbt"
}
