import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const vueRouter = new Router({
    /*mode: 'history',*/
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            name: '/',
            path: '/readme',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            children:[
                {
                    name: 'readme',
                    path: '/',
                    component: resolve => require(['../components/page/Readme.vue'], resolve)
                },
                //用户管理
                {
                    name: 'userManage',
                    path: '/userManage',
                    component: resolve => require(['../components/page/UserManage.vue'], resolve)
                },
                //盒型分类
                {
                    name: 'boxTypeClassify',
                    path: '/boxTypeClassify',
                    component: resolve => require(['../components/page/BoxTypeClassify.vue'], resolve)
                },
                //盒型列表管理
                {
                    name: 'boxTypeList',
                    path: '/boxTypeList',
                    component: resolve => require(['../components/page/BoxTypeManage.vue'], resolve)
                },
                //材质分类
                {
                    name: 'boxMaterialClassify',
                    path: '/boxMaterialClassify',
                    component: resolve => require(['../components/page/BoxMaterialClassify.vue'], resolve)
                },
                //材质列表管理
                {
                    name: 'boxMaterialList',
                    path: '/boxMaterialList',
                    component: resolve => require(['../components/page/BoxMaterialManage.vue'], resolve)
                },
                //版面分类
                {
                    name: 'boxLayoutClassify',
                    path: '/boxLayoutClassify',
                    component: resolve => require(['../components/page/BoxLayoutClassify.vue'], resolve)
                },
                //版面列表管理
                {
                    name: 'boxLayoutList',
                    path: '/boxLayoutList',
                    component: resolve => require(['../components/page/BoxLayoutList.vue'], resolve)
                },
                //印刷方式
                {
                    name: 'printMethod',
                    path: '/printMethod',
                    component: resolve => require(['../components/page/PrintMethod.vue'], resolve)
                },
                //印刷方式价格
                {
                    name: 'printMethodPrice',
                    path: '/printMethodPrice',
                    component: resolve => require(['../components/page/PrintMethodPrice.vue'], resolve)
                },
                //配送配置
                {
                    name: 'deliveryConfig',
                    path: '/deliveryConfig',
                    component: resolve => require(['../components/page/DeliveryConfig.vue'], resolve)
                },
                //方案管理
                {
                    name: 'customSpecification',
                    path: '/customSpecification',
                    component: resolve => require(['../components/page/CustomSpecification.vue'], resolve)
                },
                //未报价方案
                {
                    name: 'notQuoted',
                    path: '/notQuoted',
                    component: resolve => require(['../components/page/NotQuoted.vue'], resolve)
                },
                //已报价方案
                {
                    name: 'quoted',
                    path: '/quoted',
                    component: resolve => require(['../components/page/Quoted.vue'], resolve)
                },
                //全部订单列表
                {
                    name: 'orderList',
                    path: '/orderList',
                    component: resolve => require(['../components/page/OrderList.vue'], resolve)
                },
                //待付款订单
                {
                    name: 'pendingPay',
                    path: '/pendingPay',
                    component: resolve => require(['../components/page/PendingPay.vue'], resolve)
                },
                //待派单给厂家订单
                {
                    name: 'pendingDelivery',
                    path: '/pendingDelivery',
                    component: resolve => require(['../components/page/PendingDelivery.vue'], resolve)
                },
                //待厂家同意派单订单
                {
                    name: 'pendingConfirmDispatch',
                    path: '/pendingConfirmDispatch',
                    component: resolve => require(['../components/page/pendingConfirmDispatch.vue'], resolve)
                },


                //待发货(样品)订单
                {
                    name: 'pendingDeliverySample',
                    path: '/pendingDeliverySample',
                    component: resolve => require(['../components/page/pendingDeliverySample.vue'], resolve)
                },
                //待收货(样品)订单
                {
                    name: 'pendingReceiveSample',
                    path: '/pendingReceiveSample',
                    component: resolve => require(['../components/page/pendingReceiveSample.vue'], resolve)
                },
                //未派单订单
                {
                    name: 'noSendOrder',
                    path: '/noSendOrder',
                    component: resolve => require(['../components/page/NoSendOrder.vue'], resolve)
                },
                //已派单订单
                {
                    name: 'yesSendOrder',
                    path: '/yesSendOrder',
                    component: resolve => require(['../components/page/YesSendOrder.vue'], resolve)
                },
                //已接单订单
                {
                    name: 'acceptOrder',
                    path: '/acceptOrder',
                    component: resolve => require(['../components/page/AcceptOrder.vue'], resolve)
                },
                //已驳回订单
                {
                    name: 'refuseOrder',
                    path: '/refuseOrder',
                    component: resolve => require(['../components/page/RefuseOrder.vue'], resolve)
                },

                //待收货订单
                {
                    name: 'pendingReceipt',
                    path: '/pendingReceipt',
                    component: resolve => require(['../components/page/PendingReceipt.vue'], resolve)
                },
                //退款列表
                {
                    name: 'refundList',
                    path: '/refundList',
                    component: resolve => require(['../components/page/RefundList.vue'], resolve)
                },
                //通知人员
                {
                    name: 'notifyPersonnel',
                    path: '/notifyPersonnel',
                    component: resolve => require(['../components/page/NotifyPersonnel.vue'], resolve)
                },
                //推广人员
                {
                    name: 'promoterManage',
                    path: '/promoterManage',
                    component: resolve => require(['../components/page/PromoterManage.vue'], resolve)
                },
                //提现记录
                {
                    name: 'withdrawRecord',
                    path: '/withdrawRecord',
                    component: resolve => require(['../components/page/WithdrawRecord.vue'], resolve)
                },
                //推广海报
                {
                    name: 'posterManage',
                    path: '/posterManage',
                    component: resolve => require(['../components/page/PosterManage.vue'], resolve)
                },
                //商户列表
                {
                    name: 'merchantList',
                    path: '/merchantList',
                    component: resolve => require(['../components/page/MerchantList.vue'], resolve)
                },
                //商户提现
                {
                    name: 'merchantWithdraw',
                    path: '/merchantWithdraw',
                    component: resolve => require(['../components/page/MerchantWithdraw.vue'], resolve)
                },
                //----------------------------------------------系统管理----------------------------------------
                //部门管理
                {
                    name: 'deptManage',
                    path: '/deptManage',
                    component: resolve => require(['../components/page/system/DeptManage.vue'], resolve)
                },
                //角色管理
                {
                    name: 'roleManage',
                    path: '/roleManage',
                    component: resolve => require(['../components/page/system/RoleManage.vue'], resolve)
                }, 
                //用户管理
                {
                    name: 'sysUserManage',
                    path: '/sysUserManage',
                    component: resolve => require(['../components/page/system/SysUserManage.vue'], resolve)
                },
                //菜单管理
                {
                    name: 'menuManage',
                    path: '/menuManage',
                    component: resolve => require(['../components/page/system/MenuManage.vue'], resolve)
                },
                //参数管理
                {
                    name: 'sysConfigManage',
                    path: '/sysConfigManage',
                    component: resolve => require(['../components/page/system/SysConfigManage.vue'], resolve)
                }
            ]
        },
        {
            name: 'login',
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            name: 'upload',
            path: '/upload/:type/:number',
            component: resolve => require(['../components/page/Upload.vue'], resolve)
        }
        ,
        {
            name: 'download',
            path: '/download/:type/:number',
            component: resolve => require(['../components/page/Download.vue'], resolve)
        }
    ]
})


vueRouter.beforeEach((to, from, next) => {
    var authorization= localStorage.getItem("Authorization");
    if(authorization || to.name === 'upload' || to.name === 'download'){
        next();
    } else {
        //未登陆
        if(to.path == '/login'){//如果是登录页面路径，就直接next()
            next();
        } else {//不然就跳转到登录；
            next('/login');
        }
    }
});


export default vueRouter;