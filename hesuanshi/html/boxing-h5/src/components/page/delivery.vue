<template>
    <div class="container">
        <div class="borderView">
            <div class="main-block">
                <div class="table-text">收件人:</div>
                <input v-model="contactName" class="input"  placeholder="请输入收件人"></input>
                <!-- <div class="table-content" :style="'width:'+ (screenWidth-130) +'px;'">{{address.contactName}}</div> -->
            </div>
            <div class="main-block">
                <div class="table-text">联系电话:</div>
                <input v-model="contactPhone" class="input"  placeholder="请输入联系电话"></input>
                <!-- <div class="table-content" :style="'width:'+ (screenWidth-130) +'px;'">{{address.contactPhone}}</div> -->
            </div>
            <div class="addressView">
                <div class="table-text">收货地址:</div>
                <input v-model="contactAddress" class="input"  placeholder="请输入收货地址"></input>
                <!-- <div class="table-content" :style="'width:'+ (screenWidth-130) +'px;margin: 10px 0;'">{{address.contactAddress}}</div> -->
            </div>
        </div>
        <div class="borderView" style="margin-top: 10px;">
            <div class="main-block">
                <div class="table-text">快递公司:</div>
                <select :style="'width:'+(screenWidth-130)+'px;border: 0;font-size: 15px;appearance:none;-moz-appearance:none;-webkit-appearance:none;outline: none;'" 
                        v-model="expressId">
                    <option :value="item.id" v-for="item in express">{{item.name}}</option>
                </select>
            </div>
            <div class="addressView">
                <div class="table-text">快递单号:</div>
                <input v-model="serial" class="input" :style="'width:'+ (screenWidth-200) +'px;'" placeholder="请填写快递单号"></input>
                <div class="table-text" style="text-align: right" @click="scanQRCode">扫条形码</div>
            </div>
        </div>
        <div class='bottom-btn'>
            <div class="cancelBtn" @click="cancel">取消</div>
            <div class="confirmBtn" @click="confirm">确定发货</div>
        </div>
    </div>
</template>

<script>
    import wx from 'weixin-js-sdk';
    export default {
        data() {
            return {
                orderId: this.$route.query.orderId || 0,
                screenWidth: 0,
                address: {},
                express: [],
                serial: '',
                expressId: '',
                contactPhone: "",
                contactName: "",
                contactAddress: ""
            }
        },
        mounted () {
            var that = this;
            window.screenWidth = document.body.clientWidth;
            that.screenWidth = window.screenWidth;
            this.$axios.all([this.$axios.get('merchantOrder/getExpress'),this.$axios.get('order/selectAddressById/' + that.orderId)])
            .then(this.$axios.spread(function (express,address) {
                if(express.data.code == 200){
                    that.express = express.data.data;
                    that.expressId = that.express[0].id;
                }
                if(address.data.code == 200){
                    that.address = address.data.data;
                }
            }));
        },
        methods: {
            cancel () {
                this.$router.go(-1);//返回上一页
            },
            confirm () {
                var that = this;
                if (that.expressId === '') {
                    that.$vux.toast.text("请选择快递公司");
                    return;
                }
                if (that.serial === '') {
                    that.$vux.toast.text("快递单号不能为空");
                    return;
                }
                var params = {
                    orderId: that.orderId,
                    expressId: that.expressId,
                    serial: that.serial
                }
                that.$axios.post('merchantOrder/orderSend', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$vux.toast.text("发货成功");
                        that.$router.go(-1);//返回上一页
                    } else {
                        that.$vux.toast.text(response.data.message);
                    }
                })
            },
            scanQRCode () {
                var that = this;
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
                                jsApiList: ['scanQRCode']
                            })
                            wx.ready(function () {
                                wx.scanQRCode({
                                    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                                    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                                    success: function (res) {
                                        var result = res.resultStr.split(",");
                                        that.serial = result[1];
                                    }
                                });
                            })
                        }
                    });
            　　}else {
                    that.$vux.toast.text("请于微信端登录!");
                }
            }
        }
    }
</script>

<style scoped>
    .container {
        height: 100%;
        background: #f5f5f5;
        padding-top: 10px;
    }
    .borderView {
        background: #fff;
        border-radius:10px;
        padding: 0 15px;
        margin: 0 10px;
    }
    .main-block {
        height: 53px;
        border-bottom: 1px solid #f0f0f0;
        display: flex;
        align-items: center;
    }
    .addressView {
        min-height: 53px;
        display: flex;
        align-items: center;
    }
    .table-text {
        width: 80px;
        font-size: 15px;
        color: #888;
    }
    .table-content {
        font-size: 15px;
        color: #333;
    }
    .bottom-btn {
        display: flex;
        position: fixed;
        bottom: 0;
        width: 100%;
        height: 54px;
        background: #fff;
        align-items: center;
        /*justify-content: center;*/
    }
    .cancelBtn {
        display: flex;
        width: 50%;
        height: 54px;
        background: #fff;
        align-items: center;
        color: #888;
        font-size: 15px;
        justify-content: center;
    }
    .confirmBtn {
        display: flex;
        width: 50%;
        height: 54px;
        background: #FFBE00;
        align-items: center;
        color: #fff;
        font-size: 15px;
        justify-content: center;
    }
    .input {
        font-size: 15px;
        height: 36px;
        border: none;
        outline: none;
        margin-left: 6px;
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
</style>