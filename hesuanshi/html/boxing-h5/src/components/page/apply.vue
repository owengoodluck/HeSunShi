<template>
    <div class="container" v-if="status == -1">
        <div>
            <img src="../../assets/apply-back.png" style="width:100%;height:170px;">
        </div>
        <div class="welcome-text">欢迎加入盒算师，请填写申请信息~</div>
        <div class="block">
            <span>联系人：</span>
            <input v-model="contact" placeholder="请输入真实姓名">
        </div>
        <div class="block">
            <span>手机号：</span>
            <input v-model="phone" placeholder="请输入手机号码" pattern="[0-9]*">
        </div>
        <div class="block">
            <span>商户名称：</span>
            <input v-model="name" placeholder="请输入商户名称">
        </div>
        <div class="block">
            <span>主营项目：</span>
            <input v-model="items" placeholder="例如水果、化妆品等">
        </div>
        <div style="margin-top: -0px;">
            <group style="margin: 0px 6px;">
                <x-address title="联系地址：" v-model="addressValue" value-text-align="left" :list="addressData" placeholder="点击选择" style="font-size:14px;border: 1px solid #fff;"></x-address>
            </group>
        </div>
        <div class="address">
            <span>详细地址：</span>
            <input v-model="address" placeholder="请填写详细地址，例：22号盒算师" style="width:210px;">
        </div>
        <div style="margin-left:20px;color:rgba(187,187,187,1);">请上传您的营业执照</div>
        <div style="margin:20px;">
            <img :src="license?license:require('../../assets/apply-license.png')" style="width:80px;height:auto;">
            <input type="file" class="input-file" @change="uploadLicense" accept="image/*" ref="uploader">
        </div>
        <div style="margin: 20px;">
            <button class="apply" @click="applySubmit">立即申请</button>
        </div>
    </div>
    <div v-else-if="status == 0">
        <div>
            <img src="../../assets/review.png" style="width:100%;height:279px;">
        </div>
        <div class="review-text">您的申请已受理，系统正在审核中<br>请耐心等待…</div>
    </div>
    <div v-else>
        <div>
            <img src="../../assets/failure.png" style="width:100%;height:279px;">
        </div>
        <div class="failure-text">您好，抱歉您的申请未通过<br>请重新申请…</div>
        <div class="reApply" @click="applyEntry">再次申请</div>
    </div>
</template>

<script>
    import { Group, XAddress, ChinaAddressV4Data } from 'vux';
    export default {
        components: {
            Group,
            XAddress
        },
        created() {
            this.applyJudge();
        },
        data() {
            return {
                code: '',
                openid: '',
                unionid: '',
                status: -1,
                contact: '',
                phone: '',
                name: '',
                items: '',
                addressValue: [],
                addressData: ChinaAddressV4Data,
                address: '',
                license: ''
            }
        },
        methods: {
            getQueryString(name){
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
                if(r!=null)return  unescape(r[2]); return null;
            },
            applyJudge() {
                var that = this;
                var isWeixin = navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1;
            　　if (isWeixin) {
               　　 var code = this.getQueryString("code");
                    var oldCode = localStorage.getItem("code");
                    if (code == null || code == '' || oldCode === code) {
                        window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx06895c8a970b67f7&redirect_uri='+ encodeURIComponent('https://h5.cwzjapp.com/#/apply') + '&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect';
                    } else {
                        this.$axios.get('merchant/applyJudge/' + code)
                        .then(function (response) {
                            if (response.data.code == 200){
                                if (response.data.data.unionid != undefined) {
                                    localStorage.setItem("code", code);
                                    that.openid = response.data.data.openid;
                                    that.unionid = response.data.data.unionid;
                                } else if (response.data.data == 1) {
                                    that.$router.push('home');
                                } else {
                                   that.status = response.data.data; 
                                }
                            }
                        })
                    }
            　　}
            },
            uploadLicense() {
                var that = this;
                var file = that.$refs.uploader.files[0];
                if(file) {
                    let formData = new FormData();
                    formData.append('file', file)
                    let config = {
                        headers: {'Content-Type': 'multipart/form-data'}
                    }
                    that.$axios.post('upload/video', formData, config)
                    .then(function (response) {
                        that.license = response.data.data;
                    })
                }
            },
            applySubmit() {
                var that = this;
                if (this.contact === '') {
                    that.$vux.toast.text("联系人不能为空");
                    return;
                }
                if (this.phone === '') {
                    that.$vux.toast.text("手机号不能为空");
                    return;
                }
                if (this.name === '') {
                    that.$vux.toast.text("商户名称不能为空");
                    return;
                }
                if (this.items === '') {
                    that.$vux.toast.text("主营项目不能为空");
                    return;
                }

                if (this.addressValue.length === 0) {
                    that.$vux.toast.text("请选择联系地址");
                    return;
                }

                var province = this.addressData.find(function (obj) { 
                    if (obj.value == that.addressValue[0]) { 
                        return obj;
                    } 
                });

                var city = this.addressData.find(function (obj) { 
                    if (obj.value == that.addressValue[1]) { 
                        return obj;
                    } 
                });

                var county = this.addressData.find(function (obj) { 
                    if (obj.value == that.addressValue[2]) { 
                        return obj;
                    } 
                });

                if (this.address === '') {
                    that.$vux.toast.text("详细地址不能为空");
                    return;
                }

                if (this.license === '') {
                    that.$vux.toast.text("请上传您的营业执照");
                    return;
                }
                
                var params = {
                    unionid: this.unionid,
                    serviceNumberOpenid: this.openid,
                    contact: this.contact,
                    phone: this.phone,
                    name: this.name,
                    items: this.items,
                    province: province.name,
                    city: city.name,
                    county: county.name,
                    address: this.address,
                    license: this.license
                };
                that.$axios.post('merchant/insert', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.status = response.data.data; 
                    } else {
                        that.$vux.toast.text(response.data.message);
                    }
                })
            },
            applyEntry() {
                this.$router.push('apply');
                this.status = -1;
            }
        }
    }
</script>

<style scoped>
    .container {
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        padding-bottom: 30px;
    }
    .welcome-text {
        margin-top: 10px;
        margin-left: 10px;
        color:rgba(230,1,19,1);
    }
    .block {
        margin: 20px;
        height: 45px;
        border-bottom: 1px solid #F0F0F0;
        display: flex;
        align-items: center;
    }
    .address {
        margin: 20px;
        height: 45px;
        display: flex;
        align-items: center;
    }
    input {
        font-size:14px;
        height:36px;
        border:none;
        outline: none;
    }
    .input-file {
        width: 80px;
        height: 80px;
        position: relative;
        top: -63px;
        left: -86px;
        opacity: 0;
    }
    .apply {
        width: 100%;
        height: 49px;
        background: rgba(255,190,0,1);
        border-radius: 25px;
        border: none;
        outline: none;
        font-size: 15px;
        color: rgba(255,255,255,1);
    }
    ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
        color:#888888;
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color:#888888;
    }
    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color:#888888;
    }
    :-ms-input-placeholder { /* Internet Explorer 10-11 */
        color:#888888;
    }
    .review-text {
        margin-top: 28px;
        text-align: center;
        font-size:20px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(3,3,3,1);
        line-height:28px;
    }
    .failure-text {
        margin-top: 28px;
        text-align: center;
        font-size:20px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(3,3,3,1);
        line-height:28px;
    }
    .reApply {
        position: fixed;
        bottom: 0;
        width: 100%;
        text-align: center;
        height: 49px;
        background: rgba(255,190,0,1);
        line-height: 49px;
        font-size:15px;
        font-family:PingFangSC-Medium;
        font-weight:500;
        color:rgba(255,255,255,1);
    }
</style>