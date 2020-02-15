<template>
    <div class="container">
        <div class="title">
            <div class="center">
                <img class="avatar" :src="homeData.logo"></img>
                <div class="nameAndIntroduction">
                    <div class="name one-rows">{{homeData.name}}</div>
                    <div class="introduction one-rows">{{homeData.items}}</div>
                </div>
            </div>
            <img class="setting-icon" src="../../assets/home-setting.png" @click="goSetting"></img>
        </div>
        <div class="dataView">
            <div class="dataView-item" @click="goOrderAll">
                <div class="dataView-num">{{homeData.todayOrderNum}}</div>
                <div class="dataView-text">今日订单</div>
            </div>
            <div class="dataView-fenge"></div>
            <div class="dataView-item" @click="goMyStaff">
                <div class="dataView-num">{{homeData.myStaffNum}}</div>
                <div class="dataView-text">我的店员</div>
            </div>
        </div>
        <div class="manageView">
            <div class="manageView-titleView">
                <div class="manageView-titleView-left">订单管理</div>
                <div class="manageView-titleView-right" @click="goOrderAll">查看全部</div>
                <div style="clear:both;"></div>
            </div>
            <div class="manageView-itemView">
                <div class="manageView-item" @click="goOrder(1)">
                    <img class="manageView-item-img" src="../../assets/car.png">
                    <div class="dataView-text">待确定</div>
                    <div class="manageView-num">{{homeData.pendingOrderNum}}<font style="color: #888">单</font></div>
                </div>
                <div class="manageView-item" @click="goOrder(2)">
                    <img class="manageView-item-img" src="../../assets/box.png">
                    <div class="dataView-text">代发货</div>
                    <div class="manageView-num">{{homeData.pendingShipmentOrderNum}}<font style="color: #888888">单</font></div>
                </div>
                <div class="manageView-item">
                    <img class="manageView-item-img" src="../../assets/money.png">
                    <div class="dataView-text">维权订单</div>
                    <div class="manageView-num">{{homeData.activistOrderNum}}<font style="color: #888888">单</font></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                homeData: {}
            }
        },
        created() {
            var that = this;
            this.$axios.get('merchant/selectHomeData')
            .then(function (response) {
                if (response.data.code == 200){
                    that.homeData = response.data.data;
                }
            })
        },
        methods: {
            goSetting () {
                this.$router.push('setting');
            },
            goOrderAll () {
                this.$router.push('order');
            },
            goMyStaff () {
                this.$router.push('myStaff');
            },
            goOrder (type) {
                this.$router.push({
                    name: 'order',
                    params: {
                        type: type
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
        padding: 10px;
    }
    .title {
        height: 90px;
        background: #fff;
        border-radius:10px;
    }
    .center {
        display: flex;
        align-items: center;
        height: 90px;
        float: left;
    }
    .avatar {
        width:64px;
        height:64px;
        border-radius:50%;
        margin-left: 25px;
    }
    .nameAndIntroduction {
        margin-left: 23px;
    }
    .name {
        height:24px;
        font-size:17px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        line-height:24px;
        width: 150px;
    }
    .introduction {
        height:17px;
        font-size:12px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(136,136,136,1);
        line-height:17px;
        width: 150px;
    }
    .setting-icon {
        width:20px;
        height:20px;
        margin-top: 11px;
        margin-right: 10px;
        float: right;
    }
    .dataView {
        display: flex;
        width: auto;
        height: 111px;
        margin: 10px 0;
        background: #fff;
        border-radius:10px;
        justify-content: space-around;
        align-items: center;
    }
    .dataView-item {
        height: 56px;
        line-height: 28px;
        justify-content: center;
    }
    .dataView-fenge {
        width:2px;
        height:56px;
        background: #f2f2f2;
    }
    .dataView-num {
        font-size:22px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(255,205,54,1);
        text-align: center;
    }
    .dataView-text {
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        text-align: center;
    }
    .manageView {
        width: auto;
        height: 166px;
        margin: 10px 0;
        background: #fff;
        border-radius:10px;
        padding: 0 10px;
    }
    .manageView-titleView {
        width: auto;
        height: 40px;
        background: #fff;
        border-bottom: 1px solid #F2F2F2;
        line-height: 40px;
    }
    .manageView-titleView-left {
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        float: left;
    }
    .manageView-titleView-right {
        font-size:12px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(153,153,153,1);
        float: right;
    }
    .manageView-itemView {
        display: flex;
        width: auto;
        height: 126px;
        background: #fff;
        justify-content: space-around;
        text-align: center;
        align-items: center;
    }
    .manageView-item {}
    .manageView-item-img{
        width: 26px;
        height: 26px;
    }
    .manageView-num {
        height:17px;
        font-size:12px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(255,190,0,1);
        line-height:17px;
    }
    .one-rows {
        word-break: break-all;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        overflow: hidden;
        white-space: pre-wrap;
    }
</style>