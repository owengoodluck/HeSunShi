<template>
    <div class="container">
        <div class="borderView">
            <div v-for="(item, index) in merchantOrderDetail.orderDetailVO" style="border-bottom: 1px solid #eee;">
                <div class="imgView">
                    <div style="text-align: center;">
                        <div>
                            <img :src="item.typeImage" class="imgView-img"></img>
                        </div>
                        <div class="imgView-text">盒型: {{item.typeName}}</div>
                    </div>
                    <div style="text-align: center;">
                        <div>
                            <img :src="item.layoutImage" class="imgView-img"></img>
                        </div>
                        <div class="imgView-text">版面: {{item.layoutName}}</div>
                    </div>
                    <div style="text-align: center;">
                        <div>
                            <img :src="item.materialImage" class="imgView-img"></img>
                        </div>
                        <div class="imgView-text">材质: {{item.materialName}}</div>
                    </div>
                </div>
                <div class="lable-text">尺寸： <span class="data-text">{{item.length}}cm*{{item.width}}cm*{{item.height}}cm</span></div>
                <div class="lable-text">数量： <span class="data-text">x {{item.quantity}}</span></div>
                <div class="lable-text">印刷方式： <span class="data-text">{{item.printMethodName}}</span></div>
                <div class="lable-text">生产周期： <span class="data-text">8天</span></div>
            </div>
            <div style="height: 26px;text-align: right;font-size: 12px;color: #333;line-height: 26px;">
                共<span style="color: #F10000">{{num}}</span>个商品, 该订单可得金额 <span style="color: #F10000">¥ {{merchantOrderDetail.money}}</span>
            </div>
        </div>
        <div class="borderView" style="padding: 10px 7px;">
            <div class="lable-text">
                <div style="float: left;">收货信息： <span class="data-text">******</span></div>
                <div style="float: right;display: flex;align-items: center;" @click="goKefu">
                    <img src="../../assets/copy.png" style="width: 12px;height: 12px;">
                    <span style="height:20px;font-size:12px;color:#888;line-height:20px;margin-left: 3px;">联系客服</span>
                </div>
                <div style='clear:both;'></div>
            </div>
            <div class="lable-text">
                <div class="upload-rows" :style="'width:'+ (screenWidth-100)+'px;'">文件下载链接： <span class="data-text">{{merchantOrderDetail.resourcePath}}</span></div>
                <div class="upload" style="float: right;display: flex;align-items: center;" :data-clipboard-text="merchantOrderDetail.resourcePath" @click="doCopy">
                    <img src="../../assets/copy.png" style="width: 12px;height: 12px;">
                    <span style="height:20px;font-size:12px;color:#888;line-height:20px;margin-left: 3px;">复制</span>
                </div>
                <div style='clear:both;'></div>
            </div>
            <div class="lable-text">订单编号： <span class="data-text">{{merchantOrderDetail.orderNumber}}</span></div>
            <div class="lable-text">下单时间： <span class="data-text">{{merchantOrderDetail.createTime}}</span></div>
            <div class="lable-text" v-if="merchantOrderDetail.fareType == 0" >运费结算： <span class="data-text">寄方付</span></div>
            <div class="lable-text" v-if="merchantOrderDetail.fareType == 1" >运费结算： <span class="data-text">买方付</span></div>
            <div style="margin: 10px 0;font-size: 14px;">
                <div style="float: left;color: #888">留言内容：</div>
                <div :style="'float: right;width:'+ (screenWidth-110) +'px; color: #333'">
                    {{merchantOrderDetail.userRemark}}
                </div>
                <div style='clear:both;'></div>
            </div>
            <div style="margin: 10px 0;font-size: 14px;">
                <div style="float: left;color: #888">商家备注：</div>
                <div :style="'float: right;width:'+ (screenWidth-110) +'px; color: #333'">
                    {{merchantOrderDetail.merchantRemark}}
                </div>
                <div style='clear:both;'></div>
            </div>
        </div>
    </div>
</template>

<script>
    import Clipboard from 'clipboard';
    export default {
        data() {
            return {
                merchantOrderId: this.$route.query.merchantOrderId || 0,
                screenWidth: 0,
                merchantOrderDetail: {},
                num: 0
            }
        },
        mounted () {
            var that = this;
            window.screenWidth = document.body.clientWidth;
            that.screenWidth = window.screenWidth;
            that.$axios.get('merchantOrder/findOrderDetailByMerchantOrderId/'+ that.merchantOrderId)
            .then(function (response) {
                if (response.data.code == 200){
                    that.merchantOrderDetail = response.data.data;
                    that.num = response.data.data.orderDetailVO.length;
                }
            })
        },
        methods: {
            doCopy () {
                var that = this;
                let clipboard = new Clipboard('.upload');
                clipboard.on('success', e => {
                    console.log('复制成功')
                    that.$vux.toast.text("复制成功");
                    // 释放内存
                    clipboard.destroy()
                });

                clipboard.on('error', e => {
                    // 不支持复制
                    console.log('该浏览器不支持自动复制')
                    // 释放内存
                    clipboard.destroy()
                });
            },
            goKefu () {
                window.location.href = "http://mall.yicunyiwei.com/app/index.php?i=1&c=entry&do=chosekefu&m=cy163_customerservice";
            }
        }
    }
</script>

<style scoped>
    .container{
        background: #f5f5f5;
        padding: 10px;
        min-height: 100%;
    }
    .borderView {
        margin-bottom: 10px;
        padding: 0 7px;
        background: #fff;
        border-radius:4px;
    }
    .imgView {
        padding: 19px 0;
        display: flex;
        justify-content: space-around;
        border-bottom: 1px dashed #eee;
    }
    .imgView-img {
        width: 94px;
        height: 94px;
        border-radius: 4px;
    }
    .imgView-text {
        height:17px;
        font-size:12px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        line-height:17px;
        margin-top: 10px;
    }
    .lable-text {
        height: 20px;
        margin: 10px 0;
        font-size: 14px;
        color: #888;
    }
    .data-text {
        color: #333;
    }
    .upload-rows {
        word-break: break-all;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        overflow: hidden;
        white-space: pre-wrap;
        float: left;
        text-align: left;
        height: 20px;
        font-size: 14px;
        color: #888;
    }
</style>