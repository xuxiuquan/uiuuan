import Vue from 'vue'
import vuex from 'vuex'

Vue.use(vuex)

//引入子模块
import app from "./modules/app"
import menu from "./modules/menu"
import user from "./modules/user"
import tab from './modules/tab'
import iframe from './modules/iframe'

const store = new vuex.Store({
    modules: {
        app: app,
        menu: menu,
        user: user,
        tab: tab,
        iframe: iframe
    }
})

export default store