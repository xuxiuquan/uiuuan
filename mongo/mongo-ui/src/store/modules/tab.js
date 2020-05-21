export default {
  state: {
    // 主入口标签页
    mainTabs: [{ 
      icon: 'fa fa-home fa-lg',
      name: '系统介绍', 
      title: '系统介绍',
    }],
    // 当前标签页名
    mainTabsActiveName: ''
  },
  mutations: {
    updateMainTabs (state, tabs) {
      state.mainTabs = tabs
    },
    updateMainTabsActiveName (state, name) {
      state.mainTabsActiveName = name
    }
  }
}