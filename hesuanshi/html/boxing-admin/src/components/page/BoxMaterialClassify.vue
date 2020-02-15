<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 材质分类管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.name" placeholder="分类名称" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'boxMaterialClassify:add'">新增</el-button>
                </el-form-item>
            </el-form>
            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="#" label="状态" width="200">
                    <template slot-scope="scope">
                        <el-switch
                          v-model="scope.row.status"
                          active-value="1"
                          inactive-value="0"
                          active-color="#13ce66"
                          inactive-color=""
                          @change="updateStatus(scope.row)">
                        </el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="181">
                    <template slot-scope="scope">
                        <el-button size="small" icon="el-icon-edit"
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'boxMaterialClassify:edit'">编辑</el-button>
                    </template>
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
        <!--新增分类界面-->
        <el-dialog width="960px" title="新增材质分类" :visible.sync ="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" status-icon label-width="120px" :rules="addFormRules" ref="addForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="材质分类名称" prop="name">
                            <el-input v-model.trim="addForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
        <!--编辑界面-->
        <el-dialog width="960px" title="编辑材质分类" :visible.sync ="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" status-icon label-width="120px" :rules="editFormRules" ref="editForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="材质分类名称" prop="name">
                            <el-input v-model.trim="editForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: 'boxMaterialClassify/queryByPage',
                tableData: [],
                cur_page: 1,
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                searchForm: {
                    name: ''
                },
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    name: [
                        {required: true, message: '请输入分类名称', trigger: 'blur'}
                    ]
                },
                addForm: {
                    name: ''
                },
                editFormVisible: false,
                photo: '',
                icon: '',
                showPhotoVisible: false,
                editFormRules: {
                    name: [
                        {required: true, message: '请输入分类名称', trigger: 'blur'}
                    ]
                },
                editForm: {
                    name: ''
                }
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
                let params = '?pageNo=' + that.cur_page + '&name=' + that.searchForm.name;
                this.$axios.all([this.$axios.get(that.url + params)])
                .then(this.$axios.spread(function (res) {
                    if(res.data.code == 200){
                        if(res.data.data.list.length == 0) {
                            that.cur_page = that.cur_page - 1;
                            that.pageNo = that.pageNo - 1;
                            let params1 = '?pageNo='+ that.cur_page + '&name=' + that.searchForm.name;
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
                }));
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
                    name: ''
                };
                if (this.$refs.addForm) {
                    this.$refs.addForm.clearValidate();
                }
            },
            //新增
            addSubmit: function () {
                let that = this;
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.addForm);
                        that.$axios.post('boxMaterialClassify/insert', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '新增成功', duration: 2000});
                                that.addFormVisible = false;
                                that.search();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            },
            //显示编辑界面
            showEditDialog: function (index, row) {
                let that = this;
                that.editFormVisible = true;
                if (that.$refs.editForm) {
                    that.$refs.editForm.clearValidate();
                }
                that.editForm = Object.assign({}, row);
            },
            //编辑
            editSubmit: function () {
                let that = this;
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.editForm);
                        that.$axios.post('boxMaterialClassify/update', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '修改成功', duration: 2000});
                                that.editFormVisible = false;
                                that.search();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            },
            //更新状态
            updateStatus:function(row){
                let that = this;
                let params = Object.assign({}, row);
                that.$axios.post('boxMaterialClassify/updateStatus', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$message.success({showClose: true, message: '状态更改成功', duration: 2000});
                    } else {
                        that.$message.error({showClose: true, message: response.data.message, duration: 2000});
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
</style>