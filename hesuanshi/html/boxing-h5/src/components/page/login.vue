<template>
    <div class="container">
    	<div class="header">
    		<img src="../../assets/logo.png" style="width:82px;height:82px;">
    	</div>
        <div class="main-container">
            <div class="main-login">
            	<div class="main-block">
            		<img src="../../assets/phone.png" style="width:20px;height:20px;">
            		<input v-model="phone" placeholder="请输入手机号" class="input" pattern="[0-9]*">
            	</div>
            	<div class="main-block">
            		<img src="../../assets/code.png" style="width:20px;height:20px;">
            		<input v-model="code" placeholder="请输入验证码" class="input" style="width:100px;" pattern="[0-9]*">
                    <span style="color:#FFBE00;">|&nbsp;&nbsp;</span>
            		<button class="code" @click="getCode" :disabled="checkCode.disabled">{{checkCode.text}}</button>
            	</div>
            	<div style="margin: 26px;">
            		<button class="login" @click="loginSubmit">登录</button>
            	</div>
            </div>
        </div>
        <div class="enter">
        	还不是商户？快来<span style="color:#FFBE00;" @click="applyEntry">申请入驻</span>吧～
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                checkCode: {
                    text: '获取验证码',
                    disabled: false,
                    time: 60
                },
                phone: '',
                code: '',
                openid: '',
                unionid: ''
            }
        },
        created() {
            var isWeixin = navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1;
        　　if (isWeixin) {
           　　 var code = this.getQueryString("code");
                var oldCode = localStorage.getItem("code");
                if (code == null || code == '' || oldCode === code) {
                    window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx06895c8a970b67f7&redirect_uri='+ encodeURIComponent('https://h5.cwzjapp.com/#/login') + '&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect';
                } else {
                    this.loginCertificate();
                }
        　　}
        },
        methods: {
            getQueryString(name){
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
                if(r!=null)return  unescape(r[2]); return null;
            },
            loginCertificate() {
                var that = this;
                var code = this.getQueryString("code");
                this.$axios.get('auth/getServiceNumberLoginCertificate/' + code)
                .then(function (response) {
                    if (response.data.code == 200) {
                        localStorage.setItem("code", code);
                        that.openid = response.data.data.openid;
                        that.unionid = response.data.data.unionid;
                    }
                })
            },
            getCode() {
                var that = this;
                if (this.phone === '') {
                    that.$vux.toast.text("手机号码不能为空");
                    return;
                }

                this.$axios.get('auth/sendMerchantTextCode/' + this.phone)
                .then(function (response) {
                    if (response.data.code == 200){
                       that.checkCode.text = that.checkCode.time;
                        that.checkCode.disabled = true;
                        that.countDown(); 
                    } else {
                        that.$vux.toast.text(response.data.message);
                    }
                })
            },
            countDown() {
                var that = this;
                if (this.checkCode.text == 0) {
                    this.checkCode.text = '获取验证码';
                    this.checkCode.disabled = false;
                    return false;
                }
                var time = setTimeout(function(){
                    that.checkCode.text--;
                    that.countDown();
                }, 1000)
            },
            loginSubmit() {
                var that = this;
                if (this.phone === '') {
                    that.$vux.toast.text("手机号码不能为空");
                    return;
                }
                if (this.code === '') {
                    that.$vux.toast.text("验证码不能为空");
                    return;
                }
                var params = {
                    phone: this.phone,
                    code: this.code,
                    unionid: this.unionid,
                    openid: this.openid
                };
                that.$axios.post('auth/merchantLogin', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        localStorage.setItem("Authorization", response.data.data.token_type + response.data.data.access_token);
                        that.$router.push('home');
                    } else {
                        that.$vux.toast.text(response.data.message);
                    }
                })
            },
            applyEntry() {
                this.$router.push('apply');
            }
        }
    }
</script>

<style scoped>
	.container {
		font-size:14px;
    	font-family:PingFangSC-Regular;
    	font-weight:400;
	}
	.header {
        background: url(../../assets/login-back.png);
        background-repeat: no-repeat;
        background-size: 100% 100%;
        -moz-background-size: 100% 100%;
        -webkit-background-size: 100% 100%;
        -o-background-size: 100% 100%;
        width: 100%;
        height: 250px;
		padding-top: 61px;
		text-align: center;
	}
    .main-container {
        margin-top: -120px;
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;
    }
    .main-login {
    	height: 245px;
    	background: rgba(255,255,255,1);
    	box-shadow: 0px 2px 9px 0px rgba(0,0,0,0.13);
    	border-radius: 12px;
    }
    .main-block {
        margin: 26px;
        height: 45px;
        border-bottom: 1px solid rgba(187, 187, 187, 0.4);
        display: flex;
        align-items: center;
    }
    .input {
        font-size: 14px;
        margin-left: 6px;
        height: 36px;
        border: none;
        outline: none;
    }
    .code {
        color: #FFBE00;
        font-size: 14px;
        border: none;
        outline: none;
        background: #fff;
        padding: 0;
    }
    .enter {
    	margin-top: 30px;
    	width: 100%;
    	text-align: center;
    	color:rgba(136,136,136,1);
    }
    .login {
        width: 100%;
        height: 45px;
        background: rgba(255,190,0,1);
        border-radius: 23px;
        border: none;
        outline: none;
        font-size: 15px;
        color: rgba(255,255,255,1);
    }
    ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
    	color:rgba(177,177,177,0.48);
	}
	:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		color:rgba(177,177,177,0.48);
	}
	::-moz-placeholder { /* Mozilla Firefox 19+ */
		color:rgba(177,177,177,0.48);
	}
	:-ms-input-placeholder { /* Internet Explorer 10-11 */
		color:rgba(177,177,177,0.48);
	}
</style>