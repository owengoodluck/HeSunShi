import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import qs from 'qs';
import md5 from 'js-md5';

import "babel-polyfill";

import 'lib-flexible/flexible.js';

import store from './store';

import { AlertPlugin, ToastPlugin ,ConfirmPlugin} from 'vux';

Vue.use(AlertPlugin);
Vue.use(ToastPlugin);
Vue.use(ConfirmPlugin);


import VueScroller from 'vue-scroller'
Vue.use(VueScroller)

const key = 'yVwlsbIrY3q22EnoYYM4nR5zqTmqed05';

Vue.prototype.$axios = axios;
axios.defaults.baseURL = process.env.API_ROOT;



axios.defaults.transformRequest = [function(data) {
    if (data instanceof FormData) {
      return data
    }
    return qs.stringify(data);
}];

axios.interceptors.request.use(
    config => {
        config.headers['Authorization'] = window.localStorage.getItem("Authorization");
        var timestamp = new Date().getTime();
        config.headers['timestamp'] = timestamp;

        var dic = config.data || {};
        dic.timestamp = timestamp;

        var sdic = Object.keys(dic).sort();
        var query = '';
        for(var ki in sdic){                     
            query = query + sdic[ki] + '=' + dic[sdic[ki]] + '&';
        }

        query = query + 'key=' + key;
        config.headers['Sign'] = md5(query).toUpperCase();

        delete dic.timestamp;

        return config;
    },function(error){  
        return Promise.reject(error)  
    }
);

axios.interceptors.response.use(function (response) {  
    // token 已过期，重定向到登录页面  
    if (response.data.code == 402){  
        localStorage.clear();
        router.replace({  
            path: '/login',
            query: {redirect: router.currentRoute.fullPath}  
        });
    } else if (response.data.code == 404) {
        
    } else if (response.data.code == 500) {
        
    }

    return response  
}, function (error) {
    return Promise.reject(error)  
});

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app');