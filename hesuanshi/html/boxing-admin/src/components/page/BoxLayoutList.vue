<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 版面列表管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <!--工具条-->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model.trim="searchForm.name" placeholder="名称" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddDialog" v-hasPermission="'boxLayoutList:add'">新增</el-button>
                </el-form-item>
            </el-form>

            <!--列表-->
            <el-table :data="tableData" border ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="classifyName" label="分类名称"></el-table-column>
                <el-table-column prop="boxTypeName" label="盒型名称"></el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="#" label="图片" width="80">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.image)">
                            <img :src="scope.row.image" width="40" height="40" class="head_pic"/>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="remark" label="备注"></el-table-column>
                <el-table-column prop="#" label="状态" width="80">
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
                            @click="showEditDialog(scope.$index, scope.row)" v-hasPermission="'boxLayoutList:edit'">编辑</el-button>
                            <el-button size="small" icon="el-icon-delete" type="danger"
                            @click="deleteSubmit(scope.$index, scope.row)" v-hasPermission="'boxLayoutList:del'">删除</el-button>
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
        <!--新增材质-->
        <el-dialog width="960px" title="新增版面" :visible.sync ="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" status-icon label-width="120px" :rules="addFormRules" ref="addForm">
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="选择版面分类" prop="classifyId">
                            <el-select v-model="addForm.classifyId" placeholder="请选择">
                                <el-option
                                    v-for="item in option"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="20">
                        <el-form-item label="选择盒型" prop="boxTypeId">
                            <el-select v-model="addForm.boxTypeId" placeholder="请选择">
                                <el-option
                                    v-for="item in boxTypes"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="版面名称" prop="name">
                            <el-input v-model.trim="addForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span = "12">
                        <el-form-item label="图片" prop="image">
                            <el-upload
                                class="avatar-uploader"
                                :action="uploadXZURL"
                                :show-file-list="false"
                                :on-success="imageSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="image" :src="image" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model.trim="addForm.remark" :rows="8"></el-input>
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
        <el-dialog width="960px" title="编辑版面" :visible.sync ="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" status-icon label-width="120px" :rules="editFormRules" ref="editForm">
               <el-row >
                    <el-col :span="20">
                        <el-form-item label="选择版面分类" prop="classifyId">
                            <el-select v-model="editForm.classifyId" placeholder="请选择">
                                <el-option
                                    v-for="item in option"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="20">
                        <el-form-item label="选择盒型" prop="boxTypeId">
                            <el-select v-model="editForm.boxTypeId" placeholder="请选择">
                                <el-option
                                    v-for="item in boxTypes"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="版面名称" prop="name">
                            <el-input v-model.trim="editForm.name" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span = "12">
                        <el-form-item label="图片" prop="image">
                            <el-upload
                                class="avatar-uploader"
                                :action="uploadXZURL"
                                :show-file-list="false"
                                :on-success="imageSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="image" :src="image" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model.trim="editForm.remark" :rows="8"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit">提交</el-button>
            </div>
        </el-dialog>
        <!-- 显示图片的dialog -->
        <el-dialog :visible.sync ="showPhotoVisible" :close-on-click-modal="false" width="40%">
            <img :src="photo" class="head_pic" style="max-width:100%;max-height:100%;"/>
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
                url: 'boxLayout/queryByPage',
                uploadXZURL: this.$axios.defaults.baseURL + '/upload/uploadOneByOne/' + 'boxType',
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
                    classifyId: [
                        {required: true, message: '请选择版面分类', trigger: 'blur'}
                    ],
                    boxTypeId: [
                        {required: true, message: '请选择盒型', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入版面名称', trigger: 'blur'}
                    ],
                    image: [
                        {required: true, message: '请输上传版面图片', trigger: 'blur'}
                    ]
                },
                addForm: {
                    classifyId: '',
                    boxTypeId: '',
                    name: '',
                    image: '',
                    remark: ''
                },
                image: '',
                editFormVisible: false,
                photo: '',
                showPhotoVisible: false,
                editFormRules: {
                    classifyId: [
                        {required: true, message: '请选择版面分类', trigger: 'blur'}
                    ],
                    boxTypeId: [
                        {required: true, message: '请选择盒型', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入版面名称', trigger: 'blur'}
                    ],
                    image: [
                        {required: true, message: '请输上传版面图片', trigger: 'blur'}
                    ]
                },
                editForm: {
                    classifyId: '',
                    boxTypeId: '',
                    name: '',
                    image: '',
                    remark: ''
                },
                option: [],
                boxTypes: []
            }
        },
        mounted(){
            let that = this;
            let params = '?pageNo=' + that.cur_page + '&name=' + that.searchForm.name;
            this.$axios.all([this.$axios.get('boxLayoutClassify/findClassify'),this.$axios.get('boxType/findBoxType'),this.$axios.get(that.url + params)])
            .then(this.$axios.spread(function (classify,boxType,perms) {
                if (classify.data.code == 200){
                    that.option = classify.data.data;
                }
                if (boxType.data.code == 200){
                    that.boxTypes = boxType.data.data;
                }
                if(perms.data.code == 200){
                    that.tableData = perms.data.data.list;
                    that.total = perms.data.data.total;
                    that.pageSize = perms.data.data.pageSize;
                }
            }));
        },
        methods: {
             //上传群图片返回图片路径
            imageSuccess: function (res, file) {
                if(res.code == 200) {
                    this.image = res.data;
                    this.addForm.image = res.data;
                    this.editForm.image = res.data;
                } else {
                    this.$message.error(res.message);
                }
            },
            //上传图片规则
            beforeAvatarUpload: function (file) {  
                const isLt2M = file.size / 1024 / 1024 < 20;
                if (!isLt2M) {
                  this.$message.error('上传图片大小不能超过 20MB!');
                }
                return isLt2M;
            },
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
                    classifyId: '',
                    boxTypeId: '',
                    name: '',
                    price: '',
                    grams: '',
                    image: '',
                    remark: ''
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
                        that.$axios.post('boxLayout/insert', params)
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
                this.image = row.image;
                that.editForm = Object.assign({}, row);
            },
            //编辑
            editSubmit: function () {
                let that = this;
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.editForm);
                        that.$axios.post('boxLayout/update', params)
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
                that.$axios.post('boxLayout/updateStatus', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$message.success({showClose: true, message: '状态更改成功', duration: 2000});
                    } else {
                        that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                    }
                });
            },
            //删除
            deleteSubmit: function (index, row) {
                let that = this;
                this.$confirm('确认删除该记录吗?', '提示', {type: 'warning'}).then(() => {
                    that.$message.success({showClose: true, message: '删除成功', duration: 1500});
                    that.search();
                    that.$axios.post('boxLayout/delete/' + row.id)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.search();
                        } else {
                            that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                        }
                    });
                }).catch(() => {});
            },
            //点击查看大图片
            lookBigIcon: function (index, row) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = row;
            },
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