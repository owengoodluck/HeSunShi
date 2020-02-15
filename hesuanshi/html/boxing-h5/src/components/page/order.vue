<template>
    <div class="container">
        <div class='titleBar'>
            <font v-bind:class="type==1?'title-checked':'title'" @click="checked(1)">待确认（打样）</font>
            <font v-bind:class="type==7?'title-checked':'title'" @click="checked(7)">待发货（样品）</font>
            <font v-bind:class="type==8?'title-checked':'title'" @click="checked(8)">待收货（样品）</font>
            <font v-bind:class="type==2?'title-checked':'title'" @click="checked(2)">待生产</font>
            <font v-bind:class="type==3?'title-checked':'title'" @click="checked(3)">待收货</font>
            <font v-bind:class="type==4?'title-checked':'title'" @click="checked(4)">已完成</font>
            <font v-bind:class="type==5?'title-checked':'title'" @click="checked(5)">已关闭</font>
            <font v-bind:class="type==6?'title-checked':'title'" @click="checked(6)">已驳回</font>
        </div>
        <scroller style="top: 44px"
            :on-infinite="infinite" ref="scroller">
            <div v-for="(item, index) in orderList" class="itemView">
                <div class="numAndStatus">
                    <div class="orderNumber"> 订单编号: {{item.orderNumber}}</div>
                    <div v-if='type == 1' class="orderStatus" style="color:#FF8500;">待确认</div>
                    <div v-if='type == 91' class="orderStatus" style="color:#FF8500;">待发货（样品）</div>
                    <div v-if='type == 92' class="orderStatus" style="color:#FF8500;">待收货（样品）</div>
                    <div v-if='type == 2' class="orderStatus" style="color:#FF8500;">待生产</div>
                    <div v-if='type == 3' class="orderStatus" style="color:#FF8500;">待收货</div>
                    <div v-if='type == 4' class="orderStatus" style="color:#888;">已完成</div>
                    <div v-if='type == 5' class="orderStatus" style="color:#888;">已关闭</div>
                    <div v-if='type == 6' class="orderStatus" style="color:#888;">已驳回</div>
                    <div style='clear:both;'></div>
                </div>
                <div v-for="(item2, index2) in item.orderDetailVO" @click="orderDetail(item.id)">
                    <div style='display:flex; align-items: center;padding: 10px 0; border-bottom: 1px solid #F2F2F2'>
                        <img :src="item2.layoutImage" style='width: 94px;height: 94px;border-radius: 4px;'></img>
                        <div :style="'margin-left: 10px; width:'+ (screenWidth-144)+'px;'">
                          <div style="display: flex;justify-content: space-between;">
                            <span :style="'text-align: left; width:'+ (screenWidth-264)+'px;font-size:15px;font-weight:bold;'" class="one-rows">{{item2.specificationName}}</span>
                           <!--  <span style="text-align: right;font-size:15px;color: #F10000;">¥ {{item2.price}}</span> -->
                          </div>
                          <div style="display: flex;justify-content: space-between;margin-top: 10px;">
                            <span :style="'text-align: left; width:'+ (screenWidth-224)+'px;font-size:12px;color:#888'" class="one-rows">盒型：{{item2.typeName}}</span>
                            <span style="text-align: right;font-size:12px;color: #888;">x {{item2.quantity}}</span>
                          </div>
                          <div style='font-size:12px;color:#888;margin-top: 6px;'>材质：{{item2.materialName}}</div>
                          <div style='font-size:12px;color:#888;margin-top: 6px;'>尺寸：{{item2.length}}cm*{{item2.width}}cm*{{item2.height}}cm</div>
                        </div>
                    </div>
                </div>
                <div style="height: 26px;text-align: right;font-size: 12px;color: #333;line-height: 26px;">
                    共<span style="color: #F10000">{{item.orderDetailVO.length}}</span>个商品 <!-- 实付: <span style="color: #F10000">¥ {{item.countPrice}}</span> -->    该订单可得金额¥ {{item.money}}
                </div>
                <div v-if="type == 1" style="height: 46px;text-align: right;line-height: 46px;">
                    <button class="btnYellow" @click="confirmBtn(item)">确认打样</button>
                    <button class="btnWhite" @click="rejectBtn(item)">驳回订单</button>
                    <button class="btnWhite" @click="remarkBtn(item)">备注</button>
                </div>
                <div v-if="type == 2" style="height: 46px;text-align: right;line-height: 46px;">
                    <button class="btnYellow" @click="deliveryBtn(item)">确认发货</button>
                    <button class="btnWhite" @click="remarkBtn(item)">备注</button>
                </div>
                <div v-if="type == 3" style="height: 46px;text-align: right;line-height: 46px;">
                    <!-- <button class="btnYellow">提醒收货</button> -->
                    <button class="btnWhite" @click="remarkBtn(item)">备注</button>
                </div>
            </div>
            <div style="height: 20px;"></div>
        </scroller>
    </div>
</template>
<script>
    import { Confirm } from 'vux'
    export default {
        inject: ['reload'],
        data() {
            components: {
                Confirm
            }
            return {
                orderList: [],
                type: this.$route.params.type || 1,
                screenWidth: 0,
                pageNo: 1,
                hasNextPage: true,
                bottom: 0,
                merchantOrderStatus: 0,
                userOrderStatus: 1
            }
        },
        mounted() {
            var that = this;
            window.screenWidth = document.body.clientWidth;
            that.screenWidth = window.screenWidth;
            that.findOrderList();
        },
        methods: {
            findOrderList () {
                var that = this;
                if(that.type == 1){
                    that.merchantOrderStatus = 0;
                    that.userOrderStatus = 900;
                }else if(that.type == 2){
                    that.merchantOrderStatus = 1;
                    that.userOrderStatus = 1;
                }else if(that.type == 3){
                    that.merchantOrderStatus = 1;
                    that.userOrderStatus = 2;
                }else if(that.type == 4){
                    that.merchantOrderStatus = 1;
                    that.userOrderStatus = 3;
                }else if(that.type == 5){
                    that.merchantOrderStatus = 1;
                    that.userOrderStatus = 4;
                }else if(that.type == 6){
                    that.merchantOrderStatus = 2;
                    that.userOrderStatus = -1;
                }else if(that.type == 7){
                    that.merchantOrderStatus = 1;
                    that.userOrderStatus = 91;
                }else if(that.type == 8){
                    that.merchantOrderStatus = 1;
                    that.userOrderStatus = 92;
                }
                var params = {
                    pageNo: that.pageNo,
                    merchantOrderStatus: that.merchantOrderStatus,
                    userOrderStatus: that.userOrderStatus
                }
                that.$axios.post('merchantOrder/selectMerchantOrderByStatus', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.pageNo = that.pageNo + 1;
                        that.hasNextPage = response.data.data.hasNextPage;
                        that.orderList = that.orderList.concat(response.data.data.list);
                        that.bottom = that.bottom + response.data.data.list.length;
                    }
                })
            },
            infinite (done) {
                if(!this.hasNextPage){
                    this.$refs.scroller.finishInfinite(true);
                    this.$vux.toast.text("没有更多数据");
                } else {
                    setTimeout(() => {
                        this.findOrderList();
                        done()
                    }, 1500)
                }
            },
            checked (index) {
                var that = this;
                that.type = index;
                that.orderList = [];
                that.pageNo = 1;
                that.findOrderList();
            },
            orderDetail (id) {
                if(this.type != 6){
                    this.$router.push({
                        name: 'orderDetail',
                        query: {
                            merchantOrderId: id
                        }
                    })
                }
            },
            confirmBtn (item) {
                var that = this ;
                this.$vux.confirm.show({
                    title: "温馨提示",
                    content: "您是否确认订单进行生产",
                    onCancel : () => {},
                    onConfirm : () => {
                        that.$axios.get('merchantOrder/confirm/'+ item.id)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$vux.toast.text("成功接单");
                                that.reload();
                            }
                        })
                  }
                })
            },
            rejectBtn (item) {
                var that = this
                this.$vux.confirm.prompt('请输入驳回原因...', {
                    title: '驳回订单',
                    onCancel () {},
                    onConfirm (msg) {
                        if(msg) {
                            var params = {
                                id: item.id,
                                reason: msg
                            }
                            that.$axios.post('merchantOrder/reject', params)
                            .then(function (response) {
                                if (response.data.code == 200){
                                    that.$vux.toast.text("您已拒绝");
                                    that.reload();
                                }
                            })
                        }else {
                            that.$vux.toast.text("请输入驳回原因");
                            that.rejectBtn (item);
                        }
                    }
                })
            },
            remarkBtn (item) {
                var that = this ;
                this.$vux.confirm.prompt('请输入内容...', {
                    title: '订单备注',
                    onCancel () {},
                    onConfirm (msg) {
                        if(msg) {
                            var params = {
                                id: item.id,
                                remark: msg
                            }
                            that.$axios.post('merchantOrder/remark', params)
                            .then(function (response) {
                                if (response.data.code == 200){
                                    that.$vux.toast.text("您已备注");
                                    that.reload();
                                }
                            })
                        }else {
                            that.$vux.toast.text("请输入内容");
                            that.remarkBtn (item);
                        }
                    }
                })
            },
            deliveryBtn (item) {
                this.$router.push({
                    name: 'delivery',
                    query: {
                        orderId: item.orderId
                    }
                })
            }
        }
    }
</script>
<style scoped>
    .container {
        height: 100%;
        background: #f5f5f5;
    }
    .titleBar {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        box-shadow: 0 2px 10px 0 rgba(0,0,0,0.1);
        background-color: #fff;
        z-index: 1000;
        height: 44px;
        margin: 0px 0px 0px 0rpx;
        display: flex;
        justify-content: space-around;
        align-items: center;
        text-align: center;
    }
    .title {
        height: 44px;
        font-size: 14px;
        color: rgba(153, 153, 153, 1);
        line-height: 44px;
    }
    .title-checked {
        height: 44px;
        font-size: 14px;
        line-height: 44px;
        color: #FFBE00;
        border-bottom: 2px solid #FFBE00;
    }
    .itemView {
        margin: 10px;
        padding: 0 10px;
        background: #FFF;
        border-radius:10px;
    }
    .numAndStatus {
        height:40px;
        border-bottom:1px solid #F2F2F2;
        line-height: 40px;
    }
    .orderNumber {
        float:left;
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:bold;
        color:#333;
    }
    .orderStatus {
        float:right;
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
    }
    .one-rows {
        word-break: break-all;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        overflow: hidden;
        white-space: pre-wrap;
    }
    .btnYellow {
        width: 75px;
        height:26px;
        background: #FFBE00;
        border-radius:16px;
        padding: 0;
        border-radius: 26px;
        border: none;
        outline: none;
        color: #fff;
        font-size: 12px;

    }
    .btnWhite {
        width: 75px;
        height:26px;
        background: #fff;
        border-radius:16px;
        padding: 0;
        border-radius: 26px;
        border:1px solid rgba(187,187,187,1);
        outline: none;
        color: #333;
        font-size: 12px;
        margin-left: 10px;
    }
</style>
