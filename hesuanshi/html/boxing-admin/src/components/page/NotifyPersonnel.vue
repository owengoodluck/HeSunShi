<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 下单通知人员列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true">
                <el-form-item>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'notifyPersonnel:add'">新增</el-button>
                </el-form-item>
            </el-form>
            <!-- 列表 -->
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="nickName" label="昵称"></el-table-column>
                <el-table-column prop="#" label="用户头像">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.avatar)">
                            <img :src="scope.row.avatar" width="40" height="40" class="head_pic"/>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="createTime" label="创建时间"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="danger" icon="el-icon-delete"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'notifyPersonnel:del'">删除</el-button>
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
        </el-col>
        <!--新增系统用户界面-->
        <el-dialog title="新增通知人员" :visible.sync ="addFormVisible" :close-on-click-modal="false" width="30%">
            <el-form :model="addForm" status-icon label-width="80px" :rules="addFormRules" ref="addForm">
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="选择用户" prop="uId">
                            <el-select
                                v-model="addForm.uId"
                                filterable
                                remote
                                placeholder="请输入手机号"
                                :remote-method="findUser"
                                :loading="loading">
                                <el-option
                                    v-for="item in option"
                                    :key="item.id"
                                    :label="item.nickName"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: 'notifyPersonnel/queryByPage',
                tableData: [],
                cur_page: 1,
                select_word: '',
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                loading: false,
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    uId: [
                        {required: true, message: '请选择用户', trigger: 'blur'}
                    ]
                },
                addForm: {
                    uId: ''
                },
                option: [],
                checkedValue: [],
                showPhotoVisible: false,
                photo: ""
            }
        },
        mounted(){
            let that = this;
            that.getData();
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
                let params = '?pageNo=' + that.cur_page;
                that.$axios.get(that.url + params).then((res) => {
                    if(res.data.code == 200){
                        if(res.data.data.list.length == 0) {
                            that.cur_page = that.cur_page - 1;
                            that.pageNo = that.pageNo - 1;
                            let params1 = '?pageNo='+ that.cur_page;
                            that.$axios.get(that.url + params1).then((res) => {
                                if(res.data.code == 200){
                                    that.tableData = res.data.data.list;
                                    that.total = res.data.data.total;
                                    that.pageSize = res.data.data.pageSize;
                                }
                            })
                        } else {
                            that.tableData = res.data.data.list;
                            that.total = res.data.data.total;
                            that.pageSize = res.data.data.pageSize;
                        }
                    } 
                })
            },
            search: function (){
                this.is_search = true;
                this.cur_page = this.pageNo;
                this.getData();
            },
            //显示新增界面
            showAddDialog: function () {
                this.addFormVisible = true;
                this.addForm = {
                    uId: ""
                };
                if (this.$refs.addForm) {
                    this.$refs.addForm.clearValidate();
                    this.option = [];
                }
            },
            findUser: function (phone){
                let that = this;
                if(phone.replace(/^\s\s*/, '').replace(/\s\s*$/, '') !== ''){
                    that.$axios.get('user/findUserByPhone/' + phone).then((res) => {
                        if(res.data.code == 200){
                            that.option = res.data.data;
                        } 
                    })
                }
            },
            //提交新增系统用户
            addSubmit: function () {
                let that = this;
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.addForm);
                        that.$axios.post('notifyPersonnel/insert', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '新增成功', duration: 2000});
                                that.addFormVisible = false;
                                that.search();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 3000});
                            }
                        });
                    }
                });
            },
            //删除
            deleteSubmit: function (index, row) {
                let that = this;
                this.$confirm('确认删除该记录吗?', '提示', {type: 'warning'}).then(() => {
                    that.$axios.get('notifyPersonnel/delete/' + row.id)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.search();
                        } else {
                            that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                        }
                    });
                }).catch(() => {

                });
               
                
            },
            //点击查看大图片
            lookBigIcon: function (index, row) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = row;
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