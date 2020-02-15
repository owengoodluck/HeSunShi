<template>
    <div class="container">
        <div class="borderView">
            <img src="../../assets/tixian-money.png" class="img">
            <div class="text" style="margin-top: 14px;">可提现金额(元)</div>
            <div class="money">{{canCommission}}</div>
        </div>
        <div class="tixianView" @click="withdrawRecord" style="margin-top: 10px;border-bottom: 1px solid #f5f5f5;">
            <div style="float: left;" class="text">提现记录</div>
            <div style="float: right;"><img src="../../assets/more.png" style="width: 6px;height: 13px;"></div>
            <div style='clear:both;'></div>
        </div>
        <div class="tixianView" @click="earningsRecord">
            <div style="float: left;" class="text">收益记录</div>
            <div style="float: right;"><img src="../../assets/more.png" style="width: 6px;height: 13px;"></div>
            <div style='clear:both;'></div>
        </div>
        <div style="margin-top: 45px;padding: 0 10px;">
            <button class="tixianBtn" @click="tixianBtn">提现</button>
        </div>
    </div>
</template>

<script>
    import { Confirm } from 'vux'
    export default {
        inject: ['reload'],
        data() {
            return {
                canCommission: 0
            }
        },
        created() {
            var that = this;
            this.$axios.get('merchantOrder/selectSettlementData')
            .then(function (response) {
                if (response.data.code == 200){
                    that.canCommission = response.data.data.canCommission;
                }
            })
        },
        methods: {
            withdrawRecord () {
                this.$router.push('withdrawRecord');
            },
            earningsRecord () {
                this.$router.push('earningsRecord');
            },
            tixianBtn () {
                var that = this;
                if (that.canCommission <= 0) {
                    that.$vux.toast.text("提现金额不能小于等于0");
                } else {
                    that.$vux.confirm.show({
                        title: "温馨提示",
                        content: "您的提现金额为: " + that.canCommission +"元",
                        onCancel : () => {},
                        onConfirm : () => {
                            var params = {
                                money: that.canCommission
                            }
                            that.$axios.post('merchantOrder/withdrawableAll', params) 
                            .then(function (res) {
                                if (res.data.code == 200) {
                                    that.$vux.toast.text("提现成功,等待客服核实...");
                                    that.reload();
                                }
                            })
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .container {
        height: 100%;
        background: #f5f5f5;
    }
    .borderView {
        background: #fff;
        padding: 26px 0;
        text-align: center;
    }
    .img {
        width: 135px;
        height: 65px;
    }
    .text {
        height:20px;
        font-size:14px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
    }
    .money {
        height:56px;
        font-size:40px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        line-height:56px;
    }
    .tixianView {
        background: #fff;
        height:48px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        line-height:48px;
        padding: 0 10px;
    }
    .tixianBtn {
        width: 100%;
        height: 49px;
        background: rgba(255,190,0,1);
        border-radius: 25px;
        border: none;
        outline: none;
        font-size: 15px;
        color: rgba(255,255,255,1);
    }
    
</style>