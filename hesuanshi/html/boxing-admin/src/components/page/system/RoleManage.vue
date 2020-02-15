<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                  <el-breadcrumb-item><i class="el-icon-menu"></i> 系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>角色管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.roleName" placeholder="角色名称" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'roleManage:add'">新增</el-button>
                </el-form-item>
            </el-form>
            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe @selection-change="handleSelectionChange">
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="roleName" label="角色名称"></el-table-column>
                <el-table-column prop="remark" label="备注"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" icon="el-icon-edit"
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'roleManage:edit'">编辑</el-button>
                        <el-button size="small" type="danger" icon="el-icon-delete"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'roleManage:del'">删除</el-button>
                        <el-button size="small" type="danger" icon="el-icon-setting"
                            @click="showEmpowerDialog(scope.$index, scope.row)" v-hasPermission="'roleManage:empower'">权限配置</el-button>
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
        <!--新增角色界面-->
        <el-dialog title="新增角色" :visible.sync ="addFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="addForm" status-icon label-width="80px" :rules="addFormRules" ref="addForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="角色名称" prop="roleName">
                            <el-input v-model.trim="addForm.roleName" clearable autofocus></el-input>
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
        <!--编辑角色界面-->
        <el-dialog title="编辑角色" :visible.sync ="editFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="editForm" status-icon label-width="100px" :rules="editFormRules" ref="editForm">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="角色名称" prop="roleName">
                            <el-input v-model.trim="editForm.roleName" clearable autofocus></el-input>
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
        <!--权限分配界面-->
        <el-dialog title="权限分配" :visible.sync ="empowerFormVisible" :close-on-click-modal="false">
            <el-tree
                :data="data2"
                show-checkbox
                check-strictly
                node-key="id"
                ref="tree"
                :default-expanded-keys="expanded"
                :default-checked-keys="checked">
            </el-tree>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="empowerFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="empowerSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: 'sys_role/queryRoleByPage',
                tableData: [],
                cur_page: 1,
                select_word: '',
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                searchForm: {
                    roleName: ''
                },
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    roleName: [
                        {required: true, message: '请输入角色名称', trigger: 'blur'}
                    ]  
                },
                addForm: {
                    roleName: '',
                    remark: ''
                },
                editFormVisible: false,
                editFormRules: {
                    roleName: [
                        {required: true, message: '请输入角色名称', trigger: 'blur'}
                    ]
                },
                editForm: {
                    roleName: '',
                    remark: ''
                },
                expanded: [],
                checked: [],
                empowerFormVisible: false,
                data2: [],
                role: {
                    id: '',
                    empower: ''
                },
                multipleSelection: []
                
            }
        },
        mounted(){
            let that = this;
            this.getData();
            //获取权限树
            this.$axios.get('sys_menu/findMenuTree').then((res) => {
                console.log(res.data.data)
                if (res.data.code == 200){
                    that.data2 = res.data.data[0].children;
                }else {
                    that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                }
            })
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
                let params = '?pageNo=' + that.cur_page + '&roleName=' + that.searchForm.roleName;
                that.$axios.get(that.url + params).then((res) => {
                    if(res.data.code == 200){
                        if(res.data.data.list.length == 0) {
                            that.cur_page = that.cur_page - 1;
                            that.pageNo = that.pageNo - 1;
                            let params1 = '?pageNo='+ that.cur_page + '&roleName=' + that.searchForm.roleName;
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
                        that.$axios.post('sys_role/insertRole', params)
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
                        that.$axios.post('sys_role/updateRole', params)
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
                this.$confirm('确认删除该记录吗?', '提示', {type: 'warning'}).then(() => {
                    that.$message.success({showClose: true, message: '删除成功', duration: 1500});
                    that.search();

                    that.$axios.post('sys_role/deleteRole/' + row.id)
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
             //显示权限分配界面
            showEmpowerDialog: function (index,row) {
                let that = this;
                that.role.id = row.id;
                //获取角色权限用于回显
                that.$axios.get('sys_role/getRoleEmpower/' + row.id)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.checked = response.data.data;
                        that.expanded = response.data.data;
                        if (that.$refs.tree) {
                            that.$refs.tree.setCheckedKeys(that.checked);
                        }
                        that.empowerFormVisible = true;
                    } else {
                        that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                    }
                });
            },
            //提交赋权
            empowerSubmit: function () {
                let that = this;
                that.role.empower = that.$refs.tree.getCheckedKeys().join(",");
                //给角色赋权
                that.$axios.post('sys_role/roleEmpower', that.role)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$message.success({showClose: true, message: "赋权成功", duration: 2000});
                        that.empowerFormVisible = false;
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