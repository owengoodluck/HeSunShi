<template>
	<div>
		<!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i>用户退款列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
        	<!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-col :span="4.5">
                <el-form-item>
                    <el-select v-model="searchForm.status" placeholder="订单状态" clearable>
                        <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                    <el-form-item label="买家姓名">
                        <el-input v-model="searchForm.userName" placeholder="买家姓名" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="4.5">
                    <el-form-item label="退款编号">
                        <el-input v-model.trim="searchForm.refundNumber" placeholder="退款编号" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="4.5">
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
            <!--列表-->
            <el-table :data="tableData" ref="multipleTable" highlight-current-row stripe @selection-change="handleSelectionChange">
    	        <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="nickName" label="退款用户"></el-table-column>
                <el-table-column prop="phone" label="退款用户手机"></el-table-column>
                <el-table-column prop="refundNumber" label="退款编号"></el-table-column>
                <el-table-column prop="reason" label="退款原因"></el-table-column>
                <el-table-column prop="instruction" label="退款说明"></el-table-column>
                <el-table-column prop="money" label="退款金额"></el-table-column>
                <el-table-column prop="#" label="退款状态">
                     <template slot-scope="scope">                   
                        <div v-if="scope.row.status == 0">
                            <div>未退款</div>
                            <el-button type="primary" plain size="small" @click="refundTap(scope.$index, scope.row)" v-hasPermission="'refundList:refund'">退款</el-button>
                        </div>
                        <div v-if="scope.row.status == 1">
                            <div >退款成功</div>
                        </div>
                        <div v-if="scope.row.status == 2">
                            <div >退款失败</div>
                        </div>
                        <div v-if="scope.row.status == 3">
                            <div >退款中</div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="退款图片" width="200">
                    <template slot-scope="scope">
                        <el-button size="small"
                            @click="refundImagesShow(scope.$index, scope.row.id)">查看
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column label="订单详情" width="200">
	                    <template slot-scope="scope">
	                        <el-button size="small"
	                            @click="orderDialog(scope.$index, scope.row.orderDetailId)">查看
                            </el-button>
	                    </template>
	                </el-table-column>
	            </el-table-column>
            </el-table>
            <!--分页-->
            <div class="pagination">
                <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next"
                    :current-page.sync = "pageNo"
                    :total="total"
                    :page-size="pageSize">
                </el-pagination>
            </div>
        </el-col>
         <!-- 退款详情页面-->
        <el-dialog width="960px" title="退款详情" :visible.sync ="detialsFormVisible" :close-on-click-modal="false">
            <template :model="orderUserDetial">
                <el-form status-icon label-width="80px" style="font-weight:bold;">
                    <el-row>
                        <el-col :span="12">  
                            <el-form-item label="方案名:" >
                                <el-input v-model="orderUserDetial.specificationName" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">  
                            <el-form-item label="盒型:" >
                                <el-input v-model="orderUserDetial.typeName" disabled></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">  
                            <el-form-item label="材质:" >
                                <el-input v-model="orderUserDetial.materialName" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">  
                            <el-form-item label="版面:" >
                                <el-input v-model="orderUserDetial.layoutName" disabled></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">  
                            <el-form-item label="印刷方式:" >
                                <el-input v-model="orderUserDetial.printMethodName" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">  
                            <el-form-item label="尺寸:" >
                                <el-input v-model="chicun" disabled></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">  
                            <el-form-item label="数量:" >
                                <el-input v-model="orderUserDetial.quantity" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">  
                            <el-form-item label="价格:" >
                                <el-input v-model="orderUserDetial.price" disabled></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">  
                            <el-form-item label="运费:" >
                                <el-input v-model="orderUserDetial.fare" disabled></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </template>
        </el-dialog>
        <!-- 退款详情页面-->
        <el-dialog width="960px" title="退款图片" :visible.sync ="showRefundImagesVisible" :close-on-click-modal="false">
            <el-form>
                <el-row :span="24">
                    <div  v-for="items in refundImages">
                        <div v-if="items.resource!=''">
                            <el-col :span="8"> 
                                <el-button  @click="lookBigIcon(items.resource)">
                                    <img :src="items.resource" class="head_pic"  style="width:250px;height:250px;"/>
                                </el-button>
                            </el-col>
                        </div>
                    </div>
                </el-row>
            </el-form>
        </el-dialog>
        <!-- 显示图片的dialog -->
        <el-dialog :visible.sync ="showPhotoVisible" :close-on-click-modal="false" width="40%">
            <img :src="photo" class="head_pic" style="max-width:100%;max-height:100%;"/>
        </el-dialog>
	</div>
</template>
<script>
    export default {
	    data() {
            return {
                url: 'refund/queryByPage',
                tableData: [],
                cur_page: 1,
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                multipleSelection: [],
                searchForm: {
                    refundNumber: '',
                    userName: '',
                    status: '',
                },
                showPhotoVisible: false,
                detialsFormVisible: false,
                photo: '',
                options:[
                    {
                        value: '0',
                        label: '待退款'       
                    },
                    {
                        value: '1',
                        label: '退款成功'       
                    },
                    {
                        value: '2',
                        label: '退款失败'       
                    },
                    {
                        value: '3',
                        label: '退款中'       
                    },
                ],
                showRefundImagesVisible: false,
                refundImages: [],
                orderUserDetial: {},
                chicun: ''
            }
        },
         mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&refundNumber=' + that.searchForm.refundNumber+ '&userName=' + that.searchForm.userName+'&status='+that.searchForm.status;
            this.$axios.get(that.url + params)
            .then(function (perms) {  
                if(perms.data.code == 200){
                    that.tableData = perms.data.data.list;
                    that.total = perms.data.data.total;
                    that.pageSize = perms.data.data.pageSize;
                }
            });
        },
        methods: {
            indexMethod: function (index) {
                return (this.cur_page - 1) * 10 + index + 1;
            },
            handleCurrentChange: function (val){
                this.cur_page = val;
                this.pageNo = val;
                this.getData();
            },
            getData: function (){
                let that = this;
                let params = '?pageNo=' + that.cur_page + '&refundNumber=' + that.searchForm.refundNumber+ '&userName=' + that.searchForm.userName+'&status='+that.searchForm.status;
                this.$axios.get(that.url + params)
                .then(function (perms) {  
                    if(perms.data.code == 200){
                        that.tableData = perms.data.data.list;
                        that.total = perms.data.data.total;
                        that.pageSize = perms.data.data.pageSize;
                    }
                });
            },
            search: function () {
                this.is_search = true;
                this.cur_page = this.pageNo;
                this.getData();
            },
            //复选框发生变化事件
            handleSelectionChange: function (val) {
                this.multipleSelection = val;
            },
            //点击查看大图片
            lookBigIcon: function (row) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = row;
            },
            //退款的图片
            refundImagesShow: function(index,id){
                let that = this;
                that.$axios.post('refund/findRefundFileById/' + id).then((res) => {
                    if(res.data.code == 200){
                        that.refundImages = res.data.data;
                        that.showRefundImagesVisible = true;
                    }
                })
            },
            //显示订单详情页面
            orderDialog: function (index,orderDetailId) {
                let that = this;
                console.log(orderDetailId)
                that.$axios.post('order/findOrderDetailByDetailId/' + orderDetailId).then((res) => {
                    if(res.data.code == 200){
                        that.chicun = res.data.data.length +"cm*"+ res.data.data.width +"cm*"+ res.data.data.height + "cm";
                        that.orderUserDetial = res.data.data;
                        that.detialsFormVisible = true;
                    }
                })
            },
              //更新退款状态
            refundTap:function(index,row){
                console.log(row)
                let that = this;
                this.$confirm('确认退款吗?', '提示', {type: 'warning'}).then(() => {
                    let params = Object.assign({}, row);
                    that.$axios.post('refund/orderRefund', params)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '当前退款已处理', duration: 2000});
                            that.search();       
                        } else {
                            that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                        }
                    });
                }).catch(() => {

                });
            }
        }
    }
</script>