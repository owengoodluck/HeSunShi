<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 推广人员列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.name" placeholder="真实姓名" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                </el-form-item>
            </el-form>
            <!-- 列表 -->
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="nickName" label="微信昵称"></el-table-column>
                <el-table-column prop="#" label="用户头像">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.avatar)">
                            <img :src="scope.row.avatar" width="40" height="40" class="head_pic"/>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="content" label="资源优势"></el-table-column>
                <el-table-column prop="#" label="佣金比例(%)">
                    <template slot-scope="scope">
                        <span>{{ scope.row.commissionRate }}</span>
                        <el-button type="primary" plain size="small" style="margin-left: 30px;" @click="updateRate(scope.$index, scope.row)" v-hasPermission="'promoterManage:updateRate'">修改</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="推荐用户">
                    <template slot-scope="scope">
                        <span @click="userShow(scope.row)"><font color='#409EFF'>查看</font></span>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="佣金记录">
                    <template slot-scope="scope">
                        <span @click="commissionShow(scope.row)"><font color='#409EFF'>查看</font></span>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="提现记录">
                    <template slot-scope="scope">
                        <span @click="tixianShow(scope.row)"><font color='#409EFF'>查看</font></span>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间"></el-table-column>
                <el-table-column prop="#" label="状态" width='230'>
                        <template slot-scope="scope">
                            <div v-if="scope.row.status == 0">
                                <span >未审核</span>
                                <el-button type="primary" plain size="small" style="margin-left: 10px;" @click="enable(scope.row.id)" v-hasPermission="'promoterManage:enable'">启用</el-button>
                                <el-button type="primary" plain size="small" @click="reviewShow(scope.row)" v-hasPermission="'promoterManage:review'">审核</el-button>
                            </div>
                            <div v-if="scope.row.status == 1">
                                <span >通过</span>
                            </div>
                            <div v-if="scope.row.status == 2">
                                <span >不通过</span>
                            </div>
                            
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
        </el-col>
        <!-- 显示图片的dialog -->
        <el-dialog :visible.sync ="showPhotoVisible" :close-on-click-modal="false" width="40%">
            <img :src="photo" class="head_pic" style="max-width:100%;max-height:100%;"/>
        </el-dialog>
        <!-- 修改佣金比例 -->
        <el-dialog width="960px" title="修改佣金比例" :visible.sync ="updateRateVisible" :close-on-click-modal="false">
            <el-form :model="updateRateForm" status-icon label-width="120px" :rules="updateRateFormRules" ref="updateRateForm">
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="佣金比例(%)" prop="commissionRate">
                            <el-input type="number" v-model.trim="updateRateForm.commissionRate"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="updateRateVisible = false">取消</el-button>
                <el-button type="primary" @click.native="updateRateSubmit">提交</el-button>
            </div>
        </el-dialog>
        <!-- 审核 -->
        <el-dialog width="960px" title="审核" :visible.sync ="reviewVisible" :close-on-click-modal="false">
            <el-form :model="reviewForm" status-icon label-width="120px" :rules="reviewFormRules" ref="reviewForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item prop="status">
                            <el-radio-group v-model="reviewForm.status">
                                <el-radio :label="1">通过</el-radio>
                                <el-radio :label="2">拒绝</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="理由" prop="remark">
                    <el-input type="textarea" v-model="reviewForm.remark" :rows="8"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="reviewVisible = false">取消</el-button>
                <el-button type="primary" @click.native="reviewSubmit">提交</el-button>
            </div>
        </el-dialog>
        <!-- 推荐用户 -->
        <el-dialog width="960px" :title="userName+': 推荐的用户'" :visible.sync ="userVisible" :close-on-click-modal="false">
            <el-table :data="userData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="nickName" label="昵称"></el-table-column>
                <el-table-column prop="#" label="用户头像" width="80">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.avatar)">
                            <img :src="scope.row.avatar" width="40" height="40" class="head_pic"/>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="gender" label="性别" width="80"></el-table-column>
                <el-table-column prop="province" label="省份"></el-table-column>
                <el-table-column prop="city" label="市区" ></el-table-column>
                <el-table-column prop="phone" label="手机号" width="140"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="140"></el-table-column>
            </el-table>
        </el-dialog>
        <!-- 佣金记录 -->
        <el-dialog width="960px" :title="userName+': 佣金记录'" :visible.sync ="commissionVisible" :close-on-click-modal="false">
            <el-table :data="commissionData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="userName" label="购买用户"></el-table-column>
                <el-table-column prop="#" label="用户头像" width="80">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.avatar)">
                            <img :src="scope.row.avatar" width="40" height="40" class="head_pic"/>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="specificationName" label="方案名称"></el-table-column>
                <el-table-column prop="price" label="订单金额"></el-table-column>
                <el-table-column prop="commission" label="佣金" ></el-table-column>
                <el-table-column prop="status" label="状态" width="140"></el-table-column>
                <el-table-column prop="time" label="创建时间" width="140"></el-table-column>
            </el-table>
        </el-dialog>

        <!-- 提现记录 -->
        <el-dialog width="960px" :title="userName+': 提现记录'" :visible.sync ="tixianVisible" :close-on-click-modal="false">
            <el-table :data="tixianData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="money" label="提现金额"></el-table-column>
                <el-table-column prop="status" label="状态" ></el-table-column>
                <el-table-column prop="time" label="创建时间" width="140"></el-table-column>
            </el-table>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        data() {
            var validateNormalprice = (rule, value, callback) => {
                if(value<0){
                    callback(new Error('数量不能小于0！'));
                }else{ 
                    callback();
                } 
            };
            var validateRemark = (rule, value, callback) => {
                if(this.reviewForm.status == 2 && value == ""){
                    callback(new Error('请输入不通过的理由！'));
                }else{ 
                    callback();
                } 
            };
            return {
                url: 'promoter/queryByPage',
                tableData: [],
                cur_page: 1,
                select_word: '',
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                loading: false,
                searchForm: {
                    name: ''
                },
                addLoading: false,
                updateRateVisible: false,
                updateRateForm: {
                    id: "",
                    price: "",
                    fare: "",
                    formId: "",
                    orderNumber: "",
                    uId: "",
                    quantity: "",
                    specificationName: ""
                },
                updateRateFormRules: {
                    price: [
                        {required: true, message: '请输入佣金比例', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ]
                },
                showPhotoVisible: false,
                photo: "",
                reviewForm: {
                    status: "",
                    remark: "",
                    id: "",
                    uId: "",
                    formId: ""
                },
                reviewFormRules: {
                    status: [
                        {required: true, message: '请选择是否通过', trigger: 'blur'}
                    ],
                    remark: [
                        {validator: validateRemark, trigger: 'blur'}
                    ]
                },
                reviewVisible: false,
                userVisible: false,
                userName: "",
                userData: [],
                commissionVisible: false,
                commissionData: [],
                tixianVisible: false,
                tixianData: []
            }
        },
        mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&name=' + that.searchForm.name;
            this.$axios.all([this.$axios.get(that.url + params)])
            .then(this.$axios.spread(function (perms) {
                if(perms.data.code == 200){
                    that.tableData = perms.data.data.list;
                    that.total = perms.data.data.total;
                    that.pageSize = perms.data.data.pageSize;
                }
            }));
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
                let params = '?pageNo=' + that.cur_page +'&name=' + that.searchForm.name;
                that.$axios.get(that.url + params).then((res) => {
                    if(res.data.code == 200){
                        that.tableData = res.data.data.list;
                        that.total = res.data.data.total;
                        that.pageSize = res.data.data.pageSize;
                    } 
                })
            },
            search: function (){
                this.is_search = true;
                this.cur_page = this.pageNo;
                this.getData();
            },
            //点击查看大图片
            lookBigIcon: function (index, row) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = row;
            },
            //显示修改佣金比例页面
            updateRate: function(index,row){
                let that = this;
                that.updateRateVisible = true;
                if (that.$refs.updateRateForm) {
                    that.$refs.updateRateForm.clearValidate();
                }
                that.updateRateForm = {
                    id: row.id,
                    commissionRate: row.commissionRate
                }
            },
            //提交修改佣金比例
            updateRateSubmit: function () {
                let that = this;
                this.$refs.updateRateForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.updateRateForm);
                        that.$axios.post('promoter/updateRate', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '修改成功', duration: 2000});
                                that.updateRateVisible = false;
                                that.getData();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            },
            reviewShow: function(row){
                let that = this;
                that.reviewForm.id = row.id;
                that.reviewForm.uId = row.uId;
                that.reviewForm.formId = row.formId;
                that.reviewVisible = true;
            },
            //提交审核
            reviewSubmit: function() {
                let that = this;
                that.$refs.reviewForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, that.reviewForm);
                        that.$axios.post('promoter/review', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '审核成功', duration: 2000});
                                that.reviewVisible = false;
                                that.getData();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            },
            userShow: function(row){
                let that = this;
                that.userName = row.realName;
                that.userVisible = true;
                let params = {
                    uId: row.uId
                }
                that.$axios.post('user/findUserByRecommenderId', params)
                .then(function (res) {
                    if (res.data.code == 200){
                        that.userData = res.data.data;
                    } 
                });
            },
            commissionShow: function(row){
                let that = this;
                that.userName = row.realName;
                that.commissionVisible = true;
                let params = {
                    uId: row.uId
                }
                that.$axios.post('promoter/findCommissionRecordByUserId', params)
                .then(function (res) {
                    if (res.data.code == 200){
                        that.commissionData = res.data.data;
                    } 
                });
            },
            tixianShow: function(row){
                let that = this;
                that.userName = row.realName;
                that.tixianVisible = true;
                let params = {
                    uId: row.uId
                }
                that.$axios.post('withdraw/findWithdrawByUserId', params)
                .then(function (res) {
                    if (res.data.code == 200){
                        that.tixianData = res.data.data
                    } 
                });
            }
        }
    }
</script>

<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    font-size: 20px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
.head_pic{
   border-radius: 50%; 
}
</style>