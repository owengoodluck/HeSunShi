<template>
    <div class="container">
        <scroller
            :on-infinite="infinite" ref="scroller">
            <div v-for="(item) in items" class="listTable">
                <div class="text" style="flex:1;">{{item.contact}}</div>
                <div class="text" style="flex:1;">{{item.phone}}</div>
                <div class="delView" @click="deleteStaff(item.id)" style="flex:1;">
                    <img class="img" src="../../assets/del.png"></img>
                    <span class="text">删除</span>
                </div>
            </div>
            <div style="height:10px;"></div>
        </scroller>
        <div class='bottom-btn' @click="addStaff">
            <img class='add-img' src='../../assets/add-staff.png'></image>
            <font class='add-text'>添加店员</font>
        </div>
    </div>
</template>

<script>
    export default {
        inject: ['reload'],
        data() {
            return {
                items: [],
                pageNo: 1,
                hasNextPage: true,
                bottom: 0
            }
        },
        mounted() {
            this.selectMyStaff();
        },
        methods: {
            selectMyStaff(){
                var that = this;
                var params = {
                    pageNo: that.pageNo
                }
                that.$axios.post('merchant/selectMyStaff', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.pageNo = that.pageNo + 1;
                        that.hasNextPage = response.data.data.hasNextPage;
                        that.items = that.items.concat(response.data.data.list);
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
                        this.selectMyStaff();
                        done()
                    }, 1500)
                }
            },
            deleteStaff(id) {
                var that = this;
                that.$vux.confirm.show({
                    title: '温馨提示',
                    content: '您确定删除该店员嘛?',
                    onConfirm : () => {
                        that.$axios.get('merchant/deleteStaff/'+ id)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$vux.toast.text("成功删除");
                                that.reload();
                            }
                        })
                    }
                })
            },
            addStaff(){
                this.$router.push('addStaff');
            }
        }
    }
</script>

<style scoped>
    .container {
        background: #f5f5f5;
    }
    .listTable {
        height: 54px;
        display: flex;
        align-items: center;
        margin: 10px;
        background: #FFF;
        border-radius:10px;
        justify-content: space-around;
    }
    .text {
        font-size:15px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(136,136,136,1);
        text-align: center;
    }
    .delView {
        display: flex;
        justify-content: center;
        align-items: center;
        vertical-align: middle;
    }
    .img {
        width: 20px;
        height: 20px;
    }
    .bottom-btn {
        display: flex;
        position: fixed;
        bottom: 0;
        width: 100%;
        height: 54px;
        border: 1px solid rgba(151, 151, 151, 0.1);
        background: #fff;
        align-items: center;
        justify-content: center;
    }
    .add-img {
        width: 26px;
        height: 26px;
        margin-right: 17px;
    }
    .add-text {
        height:24px;
        font-size:17px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(51,51,51,1);
        line-height:24px;
    }
</style>