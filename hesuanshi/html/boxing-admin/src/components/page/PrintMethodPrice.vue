<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 区间价格管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-select v-model="searchForm.printMethodId" placeholder="印刷方式" clearable>
                        <el-option
                            v-for="item in option"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'printMethodPrice:add'">新增</el-button>
                </el-form-item>
            </el-form>

            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="printMethodName" label="印刷方式"></el-table-column>
                <el-table-column prop="piece" label="件数"></el-table-column>
                <el-table-column prop="price" label="价格"></el-table-column>
                <el-table-column label="操作" width="181">
                    <template slot-scope="scope">
                        <el-button size="small" icon="el-icon-edit"
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'printMethodPrice:edit'">编辑</el-button>
                            <el-button size="small" icon="el-icon-delete" type="danger"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'printMethodPrice:del'">删除</el-button>
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
        <!--新增区间价格-->
        <el-dialog width="960px" title="新增区间价格" :visible.sync ="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" status-icon label-width="120px" :rules="addFormRules" ref="addForm">
                <el-row >
                    <el-col :span="24">
                        <el-form-item label="选择印刷方式" prop="printMethodId">
                            <el-select v-model="addForm.printMethodId" placeholder="请选择">
                                <el-option
                                    v-for="item in option"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="件数" prop="piece">
                            <el-input type="number" v-model.trim="addForm.piece"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="价格" prop="price">
                            <el-input type="number" v-model.trim="addForm.price"></el-input>
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
        <el-dialog width="960px" title="编辑区间价格" :visible.sync ="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" status-icon label-width="120px" :rules="editFormRules" ref="editForm">
                <el-row >
                    <el-col :span="24">
                        <el-form-item label="选择印刷方式" prop="printMethodId">
                            <el-select v-model="editForm.printMethodId" placeholder="请选择">
                                <el-option
                                    v-for="item in option"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="件数" prop="piece">
                            <el-input type="number" v-model.trim="editForm.piece"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="价格" prop="price">
                            <el-input type="number" v-model.trim="editForm.price"></el-input>
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
            var validateNormalprice = (rule, value, callback) => {
                if(value<0){
                    callback(new Error('数量不能小于0！'));
                }else{ 
                    callback();
                } 
            };
            return {
                url: 'printMethodPrice/queryByPage',
                tableData: [],
                cur_page: 1,
                pageNo: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
                searchForm: {
                    printMethodId: ''
                },
                addFormVisible: false,
                addLoading: false,
                addFormRules: {
                    printMethodId: [
                        {required: true, message: '请选择印刷方式', trigger: 'blur'}
                    ],
                    piece: [
                        {required: true, message: '请输入件数', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ]
                },
                addForm: {
                    printMethodId: '',
                    piece: '',
                    price: ''
                },
                editFormVisible: false,
                editFormRules: {
                    printMethodId: [
                        {required: true, message: '请选择印刷方式', trigger: 'blur'}
                    ],
                    piece: [
                        {required: true, message: '请输入件数', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ]
                },
                editForm: {
                    printMethodId: '',
                    piece: '',
                    price: ''
                },
                option: []
            }
        },
        mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&printMethodId=' + that.searchForm.printMethodId;
            this.$axios.all([this.$axios.get('printMethod/findPrintMethod'),this.$axios.get(that.url + params)])
            .then(this.$axios.spread(function (classify,perms) {
                if (classify.data.code == 200){
                    that.option = classify.data.data;
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
                let params = '?pageNo=' + that.cur_page + '&printMethodId=' + that.searchForm.printMethodId;
                this.$axios.all([this.$axios.get(that.url + params)])
                .then(this.$axios.spread(function (res) {
                    if(res.data.code == 200){
                        if(res.data.data.list.length == 0) {
                            that.cur_page = that.cur_page - 1;
                            that.pageNo = that.pageNo - 1;
                            let params1 = '?pageNo='+ that.cur_page + '&printMethodId=' + that.searchForm.printMethodId;
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
                    printMethodId: '',
                    piece: '',
                    price: ''
                };
                this.image = '';
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
                        that.$axios.post('printMethodPrice/insert', params)
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
                        that.$axios.post('printMethodPrice/update', params)
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
                    that.$axios.post('printMethodPrice/delete/' + row.id)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.search();
                        } else {
                            that.$message.error({showClose: true, message: response.data.message, duration: 2000});
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
</style>