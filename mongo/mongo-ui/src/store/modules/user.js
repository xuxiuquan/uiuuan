export default {
    state: {
        perms: [], //用户权限标识合集
    },

    getters: {

    },

    mutations: {
        //设置用户权限标识合集
        setPerms(state,perms){
            state.perms = perms
        }
    }
}