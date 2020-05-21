export default {
    state: {
        appName: "Mongo Platform", //应用名称
        themeColor: "#14889A",  //主题颜色
        oldThemeColor: "14889A", //上一次主题颜色
        collapse: false,   //导航栏收缩状态
        menuRouteLoaded:false //菜单路由是否已经加载
    },

    getters: {
        collapse(state) {  //对应上面的state
            return state.collapse
        }
    },

    mutations: {
        onCollapse(state) {
            state.collapse = !state.collapse  //改变收缩状态
        },
        //改变主题颜色
        setThemeColor(state,themeColor) {
            state.oldThemeColor = state.themeColor
            state.themeColor = themeColor
        },

        menuRouteLoaded(state,menuRouteLoaded){
            state.menuRouteLoaded = menuRouteLoaded
        },
    },

    actions: {
        
    }



}