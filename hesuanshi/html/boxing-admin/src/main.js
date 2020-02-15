import Vue from 'vue';
import App from './App';
import router from './router';
import HasPermission from './components/platformCom/hasPermission'
import axios from 'axios';
import qs from 'qs';
import ElementUI from 'element-ui';
import md5 from 'js-md5';
import 'element-ui/lib/theme-chalk/index.css';    // 默认主题
import './static/css/main.css';  
import './static/css/color-dark.css';// 浅绿色主题

import "babel-polyfill";

import $ from 'jquery';

Vue.use(ElementUI);
Vue.use(HasPermission);


const key = 'DHBlcChPkcDACGW9Ken4ioVHIjKna5Jy';

Vue.prototype.$axios = axios;
axios.defaults.baseURL = process.env.API_ROOT;



axios.defaults.transformRequest = [function(data) {
    return qs.stringify(data);
}];

var loading;

axios.interceptors.request.use(
    config => {
        loading = ElementUI.Loading.service({fullscreen: true, text: '拼命加载中....'});

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
        loading.close();
        return Promise.reject(error)  
    }
);

axios.interceptors.response.use(function (response) {  
    loading.close();
    
    console.log(response)
    // token 已过期，重定向到登录页面  
    if (response.data.code == 402){  
        localStorage.clear();
        router.replace({  
            path: '/login',
            query: {redirect: router.currentRoute.fullPath}  
        });
    } else if (response.data.code == 404) {
        ElementUI.Message.error({showClose: true, message: response.data.message, duration: 2000});
    } else if (response.data.code == 500) {
        ElementUI.Message.error({showClose: true, message: response.data.message, duration: 2000});
    }

    return response  
}, function (error) {
    loading.close();
    ElementUI.Message.error({showClose: true, message: '系统繁忙，请稍后再试', duration: 2000});
    return Promise.reject(error)  
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');