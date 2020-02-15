<template>
    <div class="container">
        <div class="borderView">
            <div class="count-earnings">当前累计收益(元)</div>
            <div class="can-earnings">{{settlementData.totalCommission}}</div>
            <div class="dai-earnings">包含待结算{{settlementData.pendingCommission}}元</div>
            <div class="tixianView">
                <div style="color: #333;float: left;">可提现金额{{settlementData.canCommission}}元</div>
                <div style="color: #888;float: right;" @click="goTixian">立即提现<img src="../../assets/more.png" style="width: 6px;height: 13px;margin-left: 12px;"></div>
                <div style='clear:both;'></div>
            </div>
        </div>
        <div class="dataView">
            <div class="dataView-item">
                <div class="dataView-num">{{settlementData.yesterdayCommission}}<span class="dataView-unit">元</span></div>
                <div class="dataView-text">昨日收益</div>
            </div>
            <div class="dataView-fenge"></div>
            <div class="dataView-item" @click="goOrder(1)">
                <div class="dataView-num">{{settlementData.unconfirmed}}<span class="dataView-unit">单</span></div>
                <div class="dataView-text">未确认订单</div>
            </div>
            <div class="dataView-fenge"></div>
            <div class="dataView-item" @click="goOrder(2)">
                <div class="dataView-num">{{settlementData.confirmed}}<span class="dataView-unit">单</span></div>
                <div class="dataView-text">已接订单</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                settlementData: {}
            }
        },
        created() {
            var that = this;
            this.$axios.get('merchantOrder/selectSettlementData')
            .then(function (response) {
                if (response.data.code == 200){
                    that.settlementData = response.data.data;
                }
            })
        },
        methods: {
            goTixian () {
                this.$router.push('withdraw');
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
    .borderView {
        background: #fff;
        border-radius:10px;
        padding: 1px 0;
    }
    .count-earnings {
        height:20px;
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        line-height:20px;
        margin-top: 24px;
        text-align: center;
    }
    .can-earnings {
        height:67px;
        font-size:48px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(231,0,16,1);
        line-height:67px;
        margin-top: 7px;
        text-align: center;
    }
    .dai-earnings{
        height:17px;
        font-size:12px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(136,136,136,1);
        line-height:17px;
        text-align: center;
    }
    .tixianView {
        margin-top: 28px;
        height:47px;
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        line-height:47px;
        border-top: 1px solid #E9E8E8;
        padding: 0 10px;
    }
    .dataView {
        margin-top: 10px;
        display: flex;
        width: auto;
        background: #fff;
        border-radius:10px;
        justify-content: space-around;
        align-items: center;
        padding: 25px 0;
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
        height:28px;
        font-size:20px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        line-height:28px;
        text-align: center;
    }
    .dataView-text {
        margin-top: 6px;
        height:20px;
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(136,136,136,1);
        line-height:20px;
        text-align: center;
    }
    .dataView-unit {
        height:17px;
        font-size:12px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(136,136,136,1);
        line-height:17px;
    }
</style>