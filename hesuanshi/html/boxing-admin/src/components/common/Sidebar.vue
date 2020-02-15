<template>
    <div style="height: 100%;">

        <el-menu :default-active="onRoutes" :collapse-transition="false" background-color="#242f42" text-color="#ffffff" class="el-menu-vertical-demo" router @open="handleOpen" @close="handleClose" :collapse="isCollapse">
            <!-- 首页 -->
            <el-scrollbar wrap-class="list" wrap-style="color: red;" view-style="font-weight: bold;" view-class="view-box" :native="false">
                <el-menu-item index="readme">
                    <i class="el-icon-setting"></i>
                    <span slot="title">首页</span>
                </el-menu-item>
                <!-- 用户管理 -->
                <el-menu-item index="userManage" v-hasPermission="'userManage:show'">
                    <i class="el-icon-date"></i>
                    <span slot="title">用户管理</span>
                </el-menu-item>
                <!-- 盒型管理 -->
                <el-submenu index="boxTypeManage" v-hasPermission="'boxTypeManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>盒型管理</template>
                    <el-menu-item key="0" index="boxTypeClassify" v-hasPermission="'boxTypeClassify:show'">盒型分类</el-menu-item>
                    <el-menu-item key="1" index="boxTypeList" v-hasPermission="'boxTypeList:show'">盒型列表</el-menu-item>
                </el-submenu>
                <!-- 材质管理 -->
                <el-submenu index="boxMaterialManage" v-hasPermission="'boxMaterialManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>材质管理</template>
                    <el-menu-item key="0" index="boxMaterialClassify" v-hasPermission="'boxMaterialClassify:show'">材质分类</el-menu-item>
                    <el-menu-item key="1" index="boxMaterialList" v-hasPermission="'boxMaterialList:show'">材质列表</el-menu-item>
                </el-submenu>
                <!-- 版面管理 -->
                <el-submenu index="boxLayoutManage" v-hasPermission="'boxLayoutManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>版面管理</template>
                    <el-menu-item key="0" index="boxLayoutClassify" v-hasPermission="'boxLayoutClassify:show'">版面分类</el-menu-item>
                    <el-menu-item key="1" index="boxLayoutList" v-hasPermission="'boxLayoutList:show'">版面列表</el-menu-item>
                </el-submenu>

                <!-- 印刷管理 -->
                <el-submenu index="printManage" v-hasPermission="'printManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>印刷管理</template>
                    <el-menu-item key="0" index="printMethod" v-hasPermission="'printMethod:show'">印刷方式</el-menu-item>
                    <el-menu-item key="1" index="printMethodPrice" v-hasPermission="'printMethodPrice:show'">区间价格</el-menu-item>
                </el-submenu>
                <el-submenu index="customSpecification" v-hasPermission="'customSpecification:show'">
                    <template slot="title"><i class="el-icon-date"></i>方案管理</template>
                    <el-menu-item key="0" index="notQuoted" v-hasPermission="'notQuoted:show'">未报价</el-menu-item>
                    <el-menu-item key="1" index="quoted" v-hasPermission="'quoted:show'">已报价</el-menu-item>
                </el-submenu>
                <!-- 订单管理 -->
                <el-submenu index="orderManage" v-hasPermission="'orderManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>订单管理</template>
                    <el-menu-item key="0" index="orderList" v-hasPermission="'orderList:show'">全部订单</el-menu-item>
                    <el-menu-item key="1" index="pendingPay" v-hasPermission="'pendingPay:show'">待付款订单</el-menu-item>
                    <el-menu-item key="2" index="pendingDelivery" v-hasPermission="'pendingDelivery:show'"> 待派单给厂家订单</el-menu-item>
                    <el-menu-item key="2" index="pendingConfirmDispatch" v-hasPermission="'pendingDelivery:show'"> 待厂家同意派单订单</el-menu-item>
                    <el-menu-item key="2" index="pendingDeliverySample" v-hasPermission="'pendingDelivery:show'"> 待发货（样品）订单</el-menu-item>
                    <el-menu-item key="2" index="pendingReceiveSample" v-hasPermission="'pendingDelivery:show'"> 待发货（样品）订单</el-menu-item>
                    <el-menu-item key="2" index="noSendOrder" v-hasPermission="'noSendOrder:show'">未派单订单</el-menu-item>
                    <el-menu-item key="2" index="yesSendOrder" v-hasPermission="'yesSendOrder:show'">已派单订单</el-menu-item>
                    <el-menu-item key="2" index="acceptOrder" v-hasPermission="'acceptOrder:show'">已接单订单</el-menu-item>
                    <el-menu-item key="2" index="refuseOrder" v-hasPermission="'refuseOrder:show'">已驳回订单</el-menu-item>
                    <el-menu-item key="3" index="pendingReceipt" v-hasPermission="'pendingReceipt:show'">待收货订单</el-menu-item>
                    <el-menu-item key="1" index="refundList" v-hasPermission="'refundList:show'">退款列表</el-menu-item>
                </el-submenu>
                <!-- 推广管理 -->
                <el-submenu index="promotionManage" v-hasPermission="'promotionManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>推广管理</template>
                    <el-menu-item key="0" index="promoterManage" v-hasPermission="'promoterManage:show'">推广人员</el-menu-item>
                    <el-menu-item key="1" index="withdrawRecord" v-hasPermission="'withdrawRecord:show'">提现记录</el-menu-item>
                    <el-menu-item key="2" index="posterManage" v-hasPermission="'posterManage:show'">推广海报</el-menu-item>
                </el-submenu>
                <!-- 商户管理 -->
                <el-submenu index="merchantManage" v-hasPermission="'merchantManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>商户管理</template>
                    <el-menu-item key="0" index="merchantList" v-hasPermission="'merchantList:show'">商户列表</el-menu-item>
                    <el-menu-item key="1" index="merchantWithdraw" v-hasPermission="'merchantWithdraw:show'">商户提现</el-menu-item>
                </el-submenu>

                <!-- 配送配置 -->
                <el-menu-item index="deliveryConfig" v-hasPermission="'deliveryConfig:show'">
                    <i class="el-icon-date"></i>
                    <span slot="title">配送配置</span>
                </el-menu-item>
                <!-- 通知人员 -->
                <el-menu-item index="notifyPersonnel" v-hasPermission="'notifyPersonnel:show'">
                    <i class="el-icon-date"></i>
                    <span slot="title">通知人员</span>
                </el-menu-item>
                <!-- 系统管理 -->
                <el-submenu index="sysManage" v-hasPermission="'sysManage:show'">
                    <template slot="title"><i class="el-icon-date"></i>系统管理</template>
                    <el-menu-item key="0" index="deptManage" v-hasPermission="'deptManage:show'">机构管理</el-menu-item>
                    <el-menu-item key="1" index="roleManage" v-hasPermission="'roleManage:show'">角色管理</el-menu-item>
                    <el-menu-item key="2" index="menuManage" v-hasPermission="'menuManage:show'">菜单管理</el-menu-item>
                    <el-menu-item key="3" index="sysUserManage" v-hasPermission="'sysUserManage:show'">用户管理</el-menu-item>
                    <el-menu-item key="4" index="sysConfigManage" v-hasPermission="'sysConfigManage:show'">参数管理</el-menu-item>
                </el-submenu>
            </el-scrollbar>
        </el-menu> 
    </div>
</template>

<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    /*min-height: 400px;*/
    height: 1000%;
  }
  .el-menu--collapse{height: 100%;}
  .list {
  max-height: 800px;
  }
</style>

<script>
    export default {
    	data() {
	      return {
	        isCollapse: false,
	        colTure: 'col-409eff'
	      };
	    },
    	methods:{
    		handleOpen(key, keyPath) {
		        //console.log(key, keyPath);
	      	},
	      	handleClose(key, keyPath) {
		        //console.log(key, keyPath);
	      	},
	      	toggleBox(e){
	      		this.isCollapse = this.isCollapse == true ? false: true;
	      		e.srcElement.style.color = e.srcElement.style.color == 'rgb(64, 158, 255)' ? '' : 'rgb(64, 158, 255)';
	      		if(e.path[1].style.width == ''){
	      			e.path[1].style.width = '64px';
	      		}else if(e.path[1].style.width == '64px'){
	      			e.path[1].style.width = '200px';
	      		}else if(e.path[1].style.width == '200px'){
	      			e.path[1].style.width = '64px';
	      		}
	      	}
    	},
        computed:{
            onRoutes(index){
                return this.$route.path.replace('/','');
            }
        }
    }
</script>
