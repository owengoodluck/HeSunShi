<template>
	<div id="app" style="height:100%;">
	    <router-view v-if="isRouterAlive"></router-view>
	    <tabbar @on-index-change="onIndexChange" v-if="tabbarShow">
		    <tabbar-item :selected='select === "home"' link="/home">
		        <img slot="icon" src="./assets/home.png">
		        <img slot="icon-active" src="./assets/home-selected.png">
		        <span slot="label">工作台</span>
		    </tabbar-item>
		    <tabbar-item :selected='select === "order"' link="/order">
		    	<img slot="icon" src="./assets/order.png">
		        <img slot="icon-active" src="./assets/order-selected.png">
		        <span slot="label">订单</span>
		    </tabbar-item>
		    <tabbar-item :selected='select === "settlement"' link="/settlement">
		    	<img slot="icon" src="./assets/settlement.png">
		        <img slot="icon-active" src="./assets/settlement-selected.png">
		        <span slot="label">结算</span>
		    </tabbar-item>
		    <tabbar-item :selected='select === "setting"' link="/setting">
		        <img slot="icon" src="./assets/setting.png">
		        <img slot="icon-active" src="./assets/setting-selected.png">
		        <span slot="label">设置</span>
		    </tabbar-item>
	    </tabbar>
	</div>
</template>
<script>
	import wx from 'weixin-js-sdk';
	import { Tabbar, TabbarItem } from 'vux'
  	export default {
	    name: 'app',
	    components:{
	    	Tabbar,
	      	TabbarItem
	    },
	    provide() {
	    	return {
	    		reload: this.reload
	    	}
	    },
	    data() {
	      	return {
	      		select: "home",
	    		isRouterAlive: true
	      	}
	    },
	    created() {
            var isWeixin = navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1;
        　　if (isWeixin) {
           　　 var params = {
                    url: window.location.href
                };
                this.$axios.post('auth/getServiceNumberSignature', params)
                  .then(function (res) {
                    if (res.data.code == 200){
                        wx.config({
                            debug: false,
                            appId: res.data.data.appId,
                            timestamp: res.data.data.timestamp,
                            nonceStr: res.data.data.nonceStr,
                            signature: res.data.data.signature,
                            jsApiList: ['updateAppMessageShareData', 'updateTimelineShareData']
                        })
                        wx.ready(function () {
                        	//自定义“分享给朋友”及“分享到QQ”按钮的分享内容
                            wx.updateAppMessageShareData({ 
                                title: '盒算师：包装智能报价，包装定制管理',
                                desc: '国内首家包装行业B2B2C全产业链互联网垂直服务平台',
                                link: 'https://h5.cwzjapp.com/#/apply',
                                imgUrl: 'https://h5.cwzjapp.com/share.png'
                            })
                            //自定义“分享到朋友圈”及“分享到QQ空间”按钮的分享内容
                            wx.updateTimelineShareData({ 
                                title: '盒算师：包装智能报价，包装定制管理',
                                link: 'https://h5.cwzjapp.com/#/apply',
                                imgUrl: 'https://h5.cwzjapp.com/share.png'
                            })
                        })
                    }
                });
        　　}
        },
		computed: {
		    tabbarShow(){
		      return this.$store.getters.getTabbarShow
		    }
		},
	    watch:{
	      	$route(to,from){
		        if(to.path == '/' || to.path == '/home' || to.path == '/order' || to.path == '/settlement' || to.path == '/setting'){
			        this.select = to.name;
			        this.$store.commit('updateTabbarShow', true);
		        }else{
		          	this.$store.commit('updateTabbarShow', false);
		        }
	      	}
	    },
	    methods: {
			onIndexChange (newIndex, oldIndex) {
				//console.log(newIndex, oldIndex);
			},
			reload () {
				this.isRouterAlive = false;
				this.$nextTick(function(){
					this.isRouterAlive = true;
				})
			}
		}
  }
</script>
<style>
	.weui-tabbar {
		position: fixed !important;
	} 
	.weui-tabbar:before {
		border: none !important;
	}
	.weui-tabbar {
		background-color: #FFF !important;
	}
	a {
		text-decoration: none;	
	}
	.weui-tabbar__label {
		line-height: 0 !important;
	}
	.weui-tabbar__item span {
		color: #8E8E93 !important;
	}
	.weui-bar__item_on span {
		color: #FFCD36 !important;
	}
</style>