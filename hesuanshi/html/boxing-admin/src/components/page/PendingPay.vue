<template>
	<div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 用户待付款订单</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 表头 -->
	    <el-col :span="24" class="warp-main">
            <!-- 工具条 -->
            <el-form :inline="true" :model="searchForm">
                <el-col :span="4">
                    <el-form-item>
                        <el-input v-model.trim="searchForm.orderNumber" placeholder="订单编号" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                </el-form-item>
            </el-form>
            <!-- 列表 -->
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="nickName" label="用户昵称"></el-table-column>
                <el-table-column prop="phone" label="用户手机"></el-table-column>
                <el-table-column prop="orderNumber" label="订单编号"></el-table-column>
                <el-table-column prop="businessNumber" label="商户订单号"></el-table-column>
                <el-table-column prop="contactName" label="收货人"></el-table-column>
                <el-table-column prop="contactPhone" label="收货人号码"></el-table-column>
                <el-table-column prop="#" label="源文件">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookResource(scope.row.orderNumber)">
                            <span style="color: bule"/>查看</span>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="contactAddress" label="收货地址"></el-table-column>
                <el-table-column prop="remark" label="买家留言"></el-table-column>
                <el-table-column prop="#" label="运费结算方式">
                    <template slot-scope="scope">
                        <div v-if="scope.row.fareType == 0">
                            <span >寄方付</span>   
                        </div>
                        <div v-if="scope.row.fareType == 1">
                            <div >买家付</div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="状态">
                    <template slot-scope="scope">
                        <div v-if="scope.row.status == 0">
                            <span >待付款</span>   
                        </div>
                        <div v-if="scope.row.status == 1">
                            <div >待发货</div>
                        </div>
                        <div v-if="scope.row.status == 2">
                            <div >待收货</div>
                        </div>
                        <div v-if="scope.row.status == 3">
                            <div >交易成功</div>
                        </div>
                        <div v-if="scope.row.status == 4">
                            <div >交易取消</div>
                        </div>
                        <div v-if="scope.row.status == 5">
                            <div >交易删除</div>
                        </div>
                        <div v-if="scope.row.status == 6">
                            <div >退款中</div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="updateTime" label="创建时间" width="140"></el-table-column>
                <el-table-column prop="#" label="详情">
                    <template slot-scope="scope">
                        <el-button type="primary" plain size="small" @click="orderDetailShow(scope.$index, scope.row.id)" >查看/改价</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <div class="pagination">
                <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next"
                    :total="total"
                    :page-size="pageSize">
                </el-pagination>
            </div>
            <!-- 显示图片的dialog -->
            <el-dialog :visible.sync ="showPhotoVisible" :close-on-click-modal="false" width="40%">
                <img :src="photo" class="head_pic" style="max-width:100%;max-height:100%;"/>
            </el-dialog>
            <!-- 显示订单详情 -->
            <el-dialog :visible.sync ="showOrderDetailVisible" :close-on-click-modal="false" width="960px">
                <el-table :data="orderDetail" border  ref="multipleTable" highlight-current-row stripe>
                    <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                    <el-table-column prop="specificationName" label="方案名"></el-table-column>
                    <el-table-column prop="#" label="状态">
                        <template slot-scope="scope">
                            <div v-if="scope.row.status == 0">
                                <span >待完成</span>   
                            </div>
                            <div v-if="scope.row.status == 1">
                                <span >已完成</span>   
                            </div>
                            <div v-if="scope.row.status == 2">
                                <div >退款中</div>
                            </div>
                            <div v-if="scope.row.status == 3">
                                <span >已退款</span>   
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="#" label="盒型" width="100">
                        <template slot-scope="scope">
                            <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.typeImage)">
                                <span style="color: bule"/>{{scope.row.typeName}}</span>
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="#" label="材质" width="100">
                        <template slot-scope="scope">
                            <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.materialImage)">
                                <span style="color: bule"/>{{scope.row.materialName}}</span>
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="#" label="版面" width="100">
                        <template slot-scope="scope">
                            <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.layoutImage)">
                                <span style="color: bule"/>{{scope.row.layoutName}}</span>
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="printMethodName" label="印刷方式"></el-table-column>
                    <el-table-column prop="#" label="尺寸">
                        <template slot-scope="scope">
                            <span style="color: bule"/>{{scope.row.length}}cm*{{scope.row.width}}cm*{{scope.row.height}}cm</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="quantity" label="件数"></el-table-column>
                    <el-table-column prop="price" label="价格"></el-table-column>
                    <el-table-column prop="fare" label="运费"></el-table-column>
                    <el-table-column prop="#" label="详情">
                        <template slot-scope="scope">
                            <el-button type="primary" plain size="small" @click="gaijiaShow(scope.$index, scope.row)" >改价</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-dialog>

            <el-dialog width="960px" title="改价" :visible.sync ="gaijiaVisible" :close-on-click-modal="false">
                <el-form :model="gaijiaForm" status-icon label-width="120px" :rules="gaijiaFormRules" ref="gaijiaForm">
                    <el-row >
                        <el-col :span="12">
                            <el-form-item label="价格" prop="price">
                                <el-input type="number" v-model.trim="gaijiaForm.price"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="运费" prop="fare">
                                <el-input type="number" v-model.trim="gaijiaForm.fare"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="gaijiaVisible = false">取消</el-button>
                    <el-button type="primary" @click.native="gaijiaSubmit">提交</el-button>
                </div>
            </el-dialog>
            <!-- 资源文件列表 -->
            <el-dialog width="960px" title="资源文件列表" :visible.sync ="resourceVisible" :close-on-click-modal="false">
                <el-table :data="resourceData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                    <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                    <el-table-column prop="name" label="文件名"></el-table-column>
                    <el-table-column prop="createTime" label="上传时间"></el-table-column>
                    <el-table-column prop="file" label="资源路径"></el-table-column>
                    <el-table-column prop="#" label="操作" width="160">
                        <template slot-scope="scope">
                            <a class='download' :href='scope.row.file' download="" target="_blank" title="下载" v-hasPermission="'resource:del'">下载</a>
                            <el-button type="primary" plain size="small" @click="deleteTap(scope.row)" v-hasPermission="'resource:del'">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-dialog>
        </el-col>
	</div>
</template>

<script>
	export default {
		data(){
            var validateNormalprice = (rule, value, callback) => {
                if(value<0){
                    callback(new Error('数量不能小于0！'));
                }else{ 
                    callback();
                } 
            };
			return{
				url: 'order/queryByPage',
				tableData: [],
                cur_page: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
				searchForm: {
                    status: '',
                    orderNumber: ''
                },
                showOrderDetailVisible: false,
                orderDetail: [],
                photo: '',
                showPhotoVisible: false,
                gaijiaVisible: false,
                gaijiaForm: {
                    id: "",
                    price: "",
                    fare: "",
                    formId: "",
                    orderNumber: "",
                    uId: "",
                    quantity: "",
                    specificationName: ""
                },
                gaijiaFormRules: {
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    fare: [
                        {required: true, message: '请输入运费', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ]
                },
                orderId: "",
                showPhotoVisible: false,
                resourceVisible: false,
                resourceData: [],
                num: ''
			}
		},
		mounted(){
		    // 调用初始化方法
			this.getData();
		},
		methods:{
		    // 序列号
			indexMethod(index) {
                return (this.cur_page - 1) * 10 + index + 1;
            },
            // 分页
            handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
            // 初始化数据
			getData(){
                let that = this;
                let params = '?pageNo=' + that.cur_page + '&status=' + 0 + '&orderNumber=' + that.searchForm.orderNumber;
                that.$axios.get(that.url + params).then((res) => {
                    if(res.data.code == 200){
                        that.tableData = res.data.data.list;
                        that.total = res.data.data.total;
                        that.pageSize = res.data.data.pageSize;
                    }
                })
            },
            // 搜索
        	search(){
                this.is_search = true;
                this.cur_page = 1;
                this.getData();
            },
            //查看订单详情
            orderDetailShow: function(index,id){
                let that = this;
                that.$axios.post('order/findOrderDetailByOrderId/' + id).then((res) => {
                    if(res.data.code == 200){
                       that.orderDetail = res.data.data;
                       that.orderId = id;
                       that.showOrderDetailVisible = true;
                    }
                })
            },
            //点击查看大图片
            lookBigIcon: function (index, row) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = row;
            },
            gaijiaShow: function(index,row){
                let that = this;
                that.gaijiaVisible = true;
                if (that.$refs.gaijiaForm) {
                    that.$refs.gaijiaForm.clearValidate();
                }
                that.gaijiaForm = {
                    id: row.id,
                    price: row.price,
                    fare: row.fare,
                    formId: row.formId,
                    orderNumber: row.orderNumber,
                    uId: row.uId,
                    quantity: row.quantity,
                    specificationName: row.specificationName
                }
            },
            //编辑
            gaijiaSubmit: function () {
                let that = this;
                this.$refs.gaijiaForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.gaijiaForm);
                        that.$axios.post('order/updatePrice', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '改价成功', duration: 2000});
                                that.gaijiaVisible = false;
                                that.$axios.post('order/findOrderDetailByOrderId/' + that.orderId).then((res) => {
                                    if(res.data.code == 200){
                                       that.orderDetail = res.data.data;
                                       that.showOrderDetailVisible = true;
                                    }
                                })
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            },
            lookResource: function(num){
                let that = this;
                that.num = num;
                that.resourceVisible = true;
                that.$axios.get('resource/findResourceByNumber/' + num)
                .then(function (res) {
                    if (res.data.code == 200){
                        that.resourceData = res.data.data;
                    } 
                });
            },
            deleteTap: function(row) {
                let that = this;
                let params = Object.assign({}, row);
                this.$confirm('确认删除该源文件吗?', '提示', {type: 'warning'}).then(() => {
                    that.$axios.post('resource/delete', params)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.lookResource(that.num);
                        }
                    });
                }).catch(() => {});
            }
		}
	}
</script>

<style>
.head_pic {
    border-radius: 60px;
}
.download{
    display: inline-block;
    white-space: nowrap;
    cursor: pointer;
    background: #ecf5ff;
    border: 1px solid #409EFF;
    color: #409EFF;
    -webkit-appearance: none;
    text-align: center;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    outline: 0;
    margin: 0;
    -webkit-transition: .1s;
    transition: .1s;
    padding: 3px 13px;
    font-size: 14px;
    border-radius: 4px;
}
</style>