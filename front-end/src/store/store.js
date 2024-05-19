import { createStore } from 'vuex'

const state = {

    // isLogin: localStorage.getItem('isLogin') === 'true',
    isLogin: sessionStorage.getItem('isLogin') === 'true',
    // isRoleAdmin: localStorage.getItem('role') === 'ROLE_ADMIN'
    isRoleAdmin: sessionStorage.getItem('role') === 'ROLE_ADMIN',

    username: localStorage.getItem('username'),


};

const mutations = {
    login(state) {
        state.username = localStorage.getItem('username')
        state.isLogin = true;
        sessionStorage.setItem('isLogin', true);

        if (sessionStorage.getItem('role') === "ROLE_ADMIN") {
            state.isRoleAdmin = true
        }
    },
    logout(state) {
        state.isLogin = false;
        sessionStorage.setItem('isLogin', false);
        state.isRoleAdmin = false
    }
};

const store = createStore({

    state,
    mutations

})

export default store;
