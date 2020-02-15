<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item label="用户名称">
                    <el-input v-model.trim="searchForm.name" placeholder="用户名称" clearable></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'sysUserManage:add'">新增</el-button>
                </el-form-item>
            </el-form>
            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe @selection-change="handleSelectionChange">
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="account" label="账号"></el-table-column>
                <el-table-column prop="name" label="真实姓名"></el-table-column>
                <el-table-column prop="dept" label="机构名称"></el-table-column>
                <el-table-column prop="roleName" label="角色"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="135"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                       <!--  <el-button size="small" icon="el-icon-edit"
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'sysUserManage:edit'">编辑</el-button>
                        <el-button size="small" type="danger" icon="el-icon-delete"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'sysUserManage:del'">删除</el-button> -->
                        <el-button size="small" type="danger" icon="el-icon-setting"
                            @click="showAssignRoleDialog(scope.$index, scope.row)" v-hasPermission="'sysUserManage:assignRole'">角色分配</el-button>
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
        <!--新增系统用户界面-->
        <el-dialog title="新增用户" :visible.sync ="addFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="addForm" status-icon label-width="80px" :rules="addFormRules" ref="addForm">
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="真实姓名" prop="name">
                            <el-input v-model.trim="addForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="账号" prop="account">
                            <el-input v-model.trim="addForm.account" clearable autofocus placeholder="请输入手机号码"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="密码" prop="passWord">
                            <el-input type="password" v-model.trim="addForm.passWord" auto-complete="off"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="确认密码" prop="passWord1">
                            <el-input type="password" v-model.trim="addForm.passWord1" auto-complete="on"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="选择部门" prop="deptid">
                            <el-select v-model="addForm.deptid" placeholder="请选择">
                                <el-option
                                    v-for="item in option"
                                    :key="item.id"
                                    :label="item.deptName"
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
       
        <!--角色分配界面-->
        <el-dialog title="角色分配" :visible.sync ="assignRoleFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="assignRoleForm" status-icon label-width="100px" ref="assignRoleForm">
                <el-form-item label="选择角色">
                    <el-select v-model="checkedValue" multiple placeholder="请选择">
                        <el-option
                            v-for="item in options"
                            :key="item.id"
                            :label="item.roleName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="assignRoleFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="assignRoleSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value !== this.addForm.passWord) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validateLength = (rule, value, callback) => {
                var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,11}$/;   
                if (!patrn.exec(value)) {
                    callback(new Error('只能输入6-12个以字母开头、可带数字、“_”、“.”的字串 !'));
                } else {
                    callback();
                }
            };

            return {
                url: 'sysUser/queryUserByPage',
                uploadURL: this.$axios.defaults.baseURL + '/upload/video/'+'avatar',
                tableData: [],
                cur_page: 1,
                select_word: '',
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                searchForm: {
                    name: ''
                },
                loading: false,
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    name: [
                        {required: true, message: '请输入真实姓名', trigger: 'blur'}
                    ],
                    account: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'},
                        {pattern: /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/, message: '手机号格式错误', trigger: 'blur'}
                    ],
                    deptid: [
                        {required: true, message: '请选择部门', trigger: 'change'}
                    ],
                    passWord: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {validator: validateLength, trigger: 'blur'}
                    ],
                    passWord1: [
                        {required: true, message: '请再次输入密码', trigger: 'blur'},
                        {validator: validatePass, trigger: 'blur'},
                        {validator: validateLength, trigger: 'blur'}
                    ]  
                },
                addForm: {
                    name: '',
                    deptid: '',
                    account: '',
                    passWord: '',
                    passWord1: ''
                },
                option: [],
                options: [],
                checkedValue: [],
                assignRoleFormVisible: false,
                assignRoleForm: {
                    roleIds: '',
                    id: ''
                },
                multipleSelection: []
            }
        },
        mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&name=' + that.searchForm.name;
            this.$axios.all([this.$axios.get('sys_dept/findDept'),this.$axios.get(that.url + params),this.$axios.get('sys_role/findRole')])
            .then(this.$axios.spread(function (dept, perms, role) {
                if (dept.data.code == 200){
                    that.option = dept.data.data;
                }
                if (role.data.code == 200){
                    that.options = role.data.data;
                }
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
                that.$axios.get(that.url + params).then((res) => {
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
                    name: '',
                    deptid: '',
                    account: '',
                    passWord: '',
                    passWord1: ''
                };
                if (this.$refs.addForm) {
                    this.$refs.addForm.clearValidate();
                    this.option1 = [];
                }
            },
            //提交新增系统用户
            addSubmit: function () {
                let that = this;
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.addForm);
                        that.$axios.post('sysUser/insertUser', params)
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
            //显示角色分配界面
            showAssignRoleDialog: function (index,row) {
                let that = this;
                that.assignRoleForm.id = row.id;
                that.checkedValue = row.roleIds.split(",");//之前疏忽了,已经有了还要去查````````````
                that.assignRoleFormVisible = true;
            },
            //提交角色分配
            assignRoleSubmit: function () {
                let that = this;
                that.assignRoleFormVisible = false;
                that.assignRoleForm.roleIds = that.checkedValue.join(",");
                //给用户分配角色
                that.$axios.post('sysUser/assignRole', that.assignRoleForm)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$message.success({showClose: true, message: "分配角色成功", duration: 2000});
                        that.assignRoleFormVisible = false;
                        that.search();
                    } else {
                        that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                    }
                });
            },
            handleSelectionChange : function (val) {
                this.multipleSelection = val;
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