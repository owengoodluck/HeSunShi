<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.name" placeholder="菜单名称" clearable></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'menuManage:add'">新增</el-button>
                </el-form-item>
            </el-form>
            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="name" label="菜单名称"></el-table-column>
                <el-table-column prop="pName" label="父级名称"></el-table-column>
                <el-table-column prop="power" label="权限串"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" icon="el-icon-edit"
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'menuManage:edit'">编辑</el-button>
                        <el-button size="small" type="danger" icon="el-icon-delete"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'menuManage:del'">删除</el-button>
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
        <!--新增菜单界面-->
        <el-dialog title="新增菜单" :visible.sync ="addFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="addForm" status-icon label-width="80px" :rules="addFormRules" ref="addForm">
               <el-row>
                    <el-col :span="12">
                        <el-form-item label="菜单名称" prop="name">
                            <el-input v-model.trim="addForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="权限串" prop="power">
                            <el-input v-model.trim="addForm.power" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="父级菜单" prop="pid">
                            <el-select v-model="addForm.pid" placeholder="请选择">
                                <el-option
                                    v-for="item in options"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model.trim="addForm.tips" :rows="8"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
        <!--编辑界面-->
        <el-dialog title="编辑菜单" :visible.sync ="editFormVisible" :close-on-click-modal="false" width="960px">
            <el-form :model="editForm" status-icon label-width="100px" :rules="editFormRules" ref="editForm">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="菜单名称" prop="name">
                            <el-input v-model.trim="editForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="权限串" prop="power">
                            <el-input v-model.trim="editForm.power" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="父级菜单" prop="pid">
                            <el-select v-model="editForm.pid" placeholder="请选择">
                                <el-option
                                    v-for="item in options"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model.trim="editForm.tips" :rows="8"></el-input>
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
                url: 'sys_menu/queryMenuByPage',
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
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    name: [
                        {required: true, message: '请输入菜单名称', trigger: 'blur'}
                    ],
                    power: [
                        {required: true, message: '请输入权限字符串', trigger: 'blur'}
                    ],
                    pid: [
                        {required: true, message: '请选择父级菜单', trigger: 'change'}
                    ]
                },
                addForm: {
                    name: '',
                    power: '',
                    pid: '',
                    tips: ''
                },
                editFormVisible: false,
                editFormRules: {
                    name: [
                        {required: true, message: '请输入菜单名称', trigger: 'blur'}
                    ],
                    power: [
                        {required: true, message: '请输入权限字符串', trigger: 'blur'}
                    ],
                    pid: [
                        {required: true, message: '请选择父级菜单', trigger: 'change'}
                    ]
                },
                editForm: {
                    name: '',
                    power: '',
                    pid: '',
                    tips: ''
                },
                options: []
            }
        },
        mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&name=' + that.searchForm.name;
            this.$axios.all([this.$axios.get('sys_menu/findMenu'),this.$axios.get(that.url + params)])
            .then(this.$axios.spread(function (acct, perms) {
                if (acct.data.code == 200){
                    that.options = acct.data.data;
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
                    power: '',
                    pid: '',
                    tips: ''
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

                        that.$axios.post('sys_menu/insertMenu', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '新增成功', duration: 2000});
                                that.addFormVisible = false;
                                //that.search();
                                window.location.reload();
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
                        that.$axios.post('sys_menu/updateMenu', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '修改成功', duration: 2000});
                                that.editFormVisible = false;
                                //that.search();
                                window.location.reload();
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
                    that.$axios.post('sys_menu/deleteMenu/' + row.id)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            //that.search();
                            window.location.reload();
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