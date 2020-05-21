import Vue from 'vue'
import App from './App'
import router from './router'
import api from './http'
import global from '@/utils/global'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import i18n from './i18n'
import store from './store'

Vue.use(ElementUI)  // 引入Element
Vue.use(api)  // 引入API模块

Vue.prototype.global = global // 挂载全局配置模块

new Vue({
  el: '#app',
  i18n,
  store,
  router,
  render: h => h(App)
})