<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 商户提现列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.name" placeholder="商户姓名" clearable></el-input>
                </el-form-item>
                <el-form-item>
                        <el-select v-model="searchForm.status" placeholder="状态" clearable>
                            <el-option key="0" label="待处理" value="0"></el-option>
                            <el-option key="1" label="处理中" value="1"></el-option>
                            <el-option key="2" label="已处理" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                </el-form-item>
            </el-form>
            <!-- 列表 -->
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="contact" label="提现商户"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="money" label="提现金额"></el-table-column>
                <el-table-column prop="status" label="状态" ></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="140"></el-table-column>
                <el-table-column label="操作" width="181">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.status == '待处理'" size="small" icon="el-icon-edit"
                            @click="transfer(scope.row)" v-hasPermission="'withdrawRecord:transfer'">确认转账</el-button>
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
        
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: 'merchant/queryWithdrawByPage',
                tableData: [],
                cur_page: 1,
                select_word: '',
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                loading: false,
                searchForm: {
                    name: '',
                    status: ''
                },
                addLoading: false,
                showPhotoVisible: false,
                photo: ""
            }
        },
        mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&name=' + that.searchForm.name + '&status=' + that.searchForm.status;
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
                let params = '?pageNo=' + that.cur_page +'&name=' + that.searchForm.name + '&status=' + that.searchForm.status;
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
            transfer: function(row){
                let that = this;
                this.$confirm('确定该单已转账给商户?', '提示', {type: 'warning'}).then(() => {
                    that.$axios.get('merchant/transfer/' + row.id)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.getData();       
                        } 
                    });
                }).catch(() => {});
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