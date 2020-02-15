<template>
	<div>
		<div class="table">
			<div class="time">时间</div>
			<div class="status">状态</div>
			<div class="money">金额</div>
		</div>
		<scroller style="top: 44px"
            :on-infinite="infinite" ref="scroller">
			<div v-for="(item) in recordList">
				<div class="listTable">
					<div class="time">{{item.createTime}}</div>
					<div class="status" v-if="item.status == 0">待处理</div>
					<div class="status" v-else-if="item.status == 1" style="color: rgba(231, 0, 16, 1);">可提现</div>
					<div class="status" v-else-if="item.status == 2" style="color: #FF8500">提现中</div>
					<div class="status" v-else-if="item.status == 3">已提现</div>
					<div class="money">{{item.commission}}</div>
				</div>
			</div>
			<div style="height: 20px;"></div>
        </scroller>
	</div>
	
</template>

<script>
    export default {
        data () {
            return {
               	recordList: [],
               	pageNo: 1,
                hasNextPage: true,
                bottom: 0,
            }
        },
        mounted(){
        	this.findCommissionRecord();
        },
        methods: {
        	findCommissionRecord () {
        		var that = this;
			    var params = {	
			      	pageNo: that.pageNo
			    }
			    that.$axios.post('merchantOrder/findCommissionRecord', params)
                .then(function (response) {
                    if (response.data.code == 200){
                    	console.log(response.data.data.list)
                        that.pageNo = that.pageNo + 1;
                        that.hasNextPage = response.data.data.hasNextPage;
                        that.recordList = that.recordList.concat(response.data.data.list);
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
                        this.findCommissionRecord();
                        done()
                    }, 1500)
                }
            },
        }
    }
</script>
<style>
	.table {
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
	.listTable{
	  	height: 48px;
	  	display: flex;
	  	justify-content: space-around;
	  	align-items: center;
	  	text-align: center;
	  	border-bottom: 1px solid #ECECEC;
	}
	.time {
		width: 33%;
		font-size:15px;
		font-family:PingFangSC-Regular;
		font-weight:400;
		color:rgba(136,136,136,1);
		line-height:21px;
	}
	.status {
		width: 33%;
		font-size:15px;
		font-family:PingFangSC-Regular;
		font-weight:400;
		color:rgba(136,136,136,1);
		line-height:21px;
	}
	.money {
		width: 33%;
		font-size:15px;
		font-family:PingFangSC-Regular;
		font-weight:400;
		color:rgba(136,136,136,1);
		line-height:21px;
	}
</style>