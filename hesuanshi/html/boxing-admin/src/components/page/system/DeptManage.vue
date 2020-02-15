<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>机构管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.deptName" placeholder="机构名称" clearable></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'deptManage:add'">新增</el-button>
                </el-form-item>
            </el-form>
            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="deptName" label="机构名称"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" icon="el-icon-edit"
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'deptManage:edit'">编辑</el-button>
                        <el-button size="small" type="danger" icon="el-icon-delete"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'deptManage:del'">删除</el-button>
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
        <!--新增部门界面-->
        <el-dialog title="新增机构" :visible.sync ="addFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="addForm" status-icon label-width="80px" :rules="addFormRules" ref="addForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="机构名称" prop="deptName">
                            <el-input v-model.trim="addForm.deptName" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model.trim="addForm.remark" :rows="8"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
        <!--编辑部门界面-->
        <el-dialog title="编辑机构" :visible.sync ="editFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="editForm" status-icon label-width="100px" :rules="editFormRules" ref="editForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="机构名称" prop="deptName">
                            <el-input v-model.trim="editForm.deptName" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                 <el-form-item label="备注">
                    <el-input type="textarea" v-model.trim="editForm.remark" :rows="8"></el-input>
                </el-form-item>
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
                url: 'sys_dept/queryDeptByPage',
                tableData: [],
                cur_page: 1,
                select_word: '',
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                searchForm: {
                    deptName: ''
                },
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    deptName: [
                        {required: true, message: '请输入机构名称', trigger: 'blur'}
                    ]  
                },
                addForm: {
                    deptName: '',
                    remark: ''
                },
                editFormVisible: false,
                editFormRules: {
                    deptName: [
                        {required: true, message: '请输入机构名称', trigger: 'blur'}
                    ]
                },
                editForm: {
                    deptName: '',
                    remark: ''
                }
            }
        },
        mounted(){
            this.getData();
        },
        methods: {
            indexMethod: function(index) {
                return (this.cur_page - 1) * 10 + index + 1;
            },
            handleCurrentChange: function(val){
                this.cur_page = val;
                this.pageNo = val;
                this.getData();
            },
            getData: function(){
                let that = this;
                let params = '?pageNo=' + that.cur_page + '&deptName=' + that.searchForm.deptName;
                that.$axios.get(that.url + params).then((res) => {
                    if(res.data.code == 200){
                        if(res.data.data.list.length == 0) {
                            that.cur_page = that.cur_page - 1;
                            that.pageNo = that.pageNo - 1;
                            let params1 = '?pageNo='+ that.cur_page + '&deptName=' + that.searchForm.deptName;
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
            search: function(){
                this.is_search = true;
                this.cur_page = this.pageNo;
                this.getData();
            },
            //显示新增界面
            showAddDialog: function () {
                this.addFormVisible = true;
                this.addForm = {
                    deptName: '',
                    remark: ''
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
                        that.$axios.post('sys_dept/insertDept', params)
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
                if(row.deptName == '中青汇智'){
                    that.$message.error({showClose: true, message: '中青汇智不能被修改', duration: 2000});
                }else {
                    that.editFormVisible = true;
                    if (that.$refs.editForm) {
                        that.$refs.editForm.clearValidate();
                    }
                    /*that.$axios.get('workExp/detail/' + row.id)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.editForm = Object.assign({}, response.data.data);
                        }
                    });*/
                    that.editForm = Object.assign({}, row);
                }
                
            },
            //编辑
            editSubmit: function () {
                let that = this;
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.editForm);
                        that.$axios.post('sys_dept/updateDept', params)
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
            //删除
            deleteSubmit: function (index, row) {
                let that = this;
                if(row.deptName == '中青汇智'){
                    that.$message.error({showClose: true, message: '中青汇智不能被删除', duration: 2000});
                } else {
                    this.$confirm('确认删除该记录吗?', '提示', {type: 'warning'}).then(() => {
                    that.$message.success({showClose: true, message: '删除成功', duration: 1500});
                    that.search();
                    that.$axios.post('sys_dept/deleteDept/' + row.id)
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
                }
                
            }
        }
    }
</script>