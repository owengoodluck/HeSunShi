import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const vueRouter = new Router({
    // mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
    	{
            name: "login",
            path: '/login',
            component: resolve => require(['../components/page/login.vue'], resolve),
            meta: {
                title: "盒算师商户",
                requiredAuth: false
            }
        },
        {
            name: "apply",
            path: '/apply',
            component: resolve => require(['../components/page/apply.vue'], resolve),
            meta: {
                title: "商户入驻申请",
                requiredAuth: false
            }
        },
        {
            name: "home",
            path: '/home',
            component: resolve => require(['../components/page/home.vue'], resolve),
            meta: {
                title: "工作台",
                requiredAuth: true
            }
        },
        {
            name: "order",
            path: '/order',
            component: resolve => require(['../components/page/order.vue'], resolve),
            meta: {
                title: "订单",
                requiredAuth: true
            }
        },
        {
            name: "orderDetail",
            path: '/orderDetail',
            component: resolve => require(['../components/page/orderDetail.vue'], resolve),
            meta: {
                title: "订单详情",
                requiredAuth: true
            }
        },
        {
            name: "delivery",
            path: '/delivery',
            component: resolve => require(['../components/page/delivery.vue'], resolve),
            meta: {
                title: "确认发货",
                requiredAuth: true
            }
        },
        {
            name: "settlement",
            path: '/settlement',
            component: resolve => require(['../components/page/settlement.vue'], resolve),
            meta: {
                title: "结算",
                requiredAuth: true
            }
        },
        {
            name: "withdraw",
            path: '/withdraw',
            component: resolve => require(['../components/page/withdraw.vue'], resolve),
            meta: {
                title: "提现",
                requiredAuth: true
            }
        },
        {
            name: "earningsRecord",
            path: '/earningsRecord',
            component: resolve => require(['../components/page/earningsRecord.vue'], resolve),
            meta: {
                title: "收益",
                requiredAuth: true
            }
        },
        {
            name: "withdrawRecord",
            path: '/withdrawRecord',
            component: resolve => require(['../components/page/withdrawRecord.vue'], resolve),
            meta: {
                title: "提现记录",
                requiredAuth: true
            }
        },
        {
            name: "setting",
            path: '/setting',
            component: resolve => require(['../components/page/setting.vue'], resolve),
            meta: {
                title: "设置",
                requiredAuth: true
            }
        },
        {
            name: "myStaff",
            path: '/myStaff',
            component: resolve => require(['../components/page/myStaff.vue'], resolve),
            meta: {
                title: "我的店员",
                requiredAuth: true
            }
        },
        {
            name: "addStaff",
            path: '/addStaff',
            component: resolve => require(['../components/page/addStaff.vue'], resolve),
            meta: {
                title: "添加店员",
                requiredAuth: true
            }
        },
        {
            name: "test",
            path: '/test',
            component: resolve => require(['../components/page/test.vue'], resolve),
            meta: {
                title: "3D产品展示",
                requiredAuth: false
            }
        },
        {
            name: "testPanorama",
            path: '/testPanorama',
            component: resolve => require(['../components/page/testPanorama.vue'], resolve),
            meta: {
                title: "3D全景展示",
                requiredAuth: false
            }
        },
        {
            name: "testPhotoSphere",
            path: '/testPhotoSphere',
            component: resolve => require(['../components/page/testPhotoSphere.vue'], resolve),
            meta: {
                title: "3D全景展示",
                requiredAuth: false
            }
        }
    ]
})


vueRouter.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title;
    }
    var authorization= localStorage.getItem("Authorization");
    if(authorization || !to.meta.requiredAuth){
        next();
    } else {
        next('/login'); 
    }
});

export default vueRouter;