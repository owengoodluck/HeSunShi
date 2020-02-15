<template>
    <div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 配送配置管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-col :span="24" class="warp-main">
            <el-form :model="deliveryConfig" >
                <el-col :span="24" class="warp-main" style="width: 80%;">
                    <el-col :span="24">  
                        <el-form-item label="计价公式:">
                            <span>{{deliveryConfig.formula}}</span></br>
                            <span style="margin-left: 80px;color: #999;" >材质克数(重量)=$G,计价公式=$Y,配件=$P,报价=$M,数量=$N,首重费用=$F,续重费用=$C,首重重量=$V,续重重量=$Q(温馨提示: 字符变量都为单字母大写)</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">  
                        <el-form-item label="首重重量:">
                            <span>{{deliveryConfig.firstWeight}}g</span>
                        </el-form-item>
                        <el-form-item label="续重重量:">
                            <span>{{deliveryConfig.continuedWeight}}g</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">  
                        <el-form-item label="首重费用:">
                            <span>{{deliveryConfig.firstCost}}元</span>
                        </el-form-item>
                        <el-form-item label="续重费用:">
                            <span>{{deliveryConfig.continuedCost}}元</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">  
                        <el-button type="primary" @click.native="editShow" v-hasPermission="'deliveryConfig:edit'">编辑</el-button>
                    </el-col>
                </el-col> 
            </el-form>
        </el-col>

        <el-dialog width="960px" title="编辑" :visible.sync ="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" status-icon label-width="120px" :rules="editFormRules" ref="editForm">
                <el-row >
                    <el-col :span="20">
                        <el-form-item label="计价公式" prop="formula">
                            <el-input v-model.trim="editForm.formula" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="12">
                        <el-form-item label="首重重量" prop="firstWeight">
                            <el-input type="number" v-model.trim="editForm.firstWeight"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="续重重量" prop="continuedWeight">
                            <el-input type="number" v-model.trim="editForm.continuedWeight"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :span="12">
                        <el-form-item label="首重费用" prop="firstCost">
                            <el-input type="number" v-model.trim="editForm.firstCost"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="续重费用" prop="continuedCost">
                            <el-input type="number" v-model.trim="editForm.continuedCost"></el-input>
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
                url: 'boxType/queryDeliveryConfig',
                deliveryConfig: {},
                addLoading: false,
                editFormVisible: false,
                editForm: {
                    id: '',
                    formula: '',
                    firstWeight: '',
                    continuedWeight: '',
                    firstCost: '',
                    continuedCost: '',
                },
                editFormRules: {
                    firstWeight: [
                        {required: true, message: '请输入首重重量', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    continuedWeight: [
                        {required: true, message: '请输入续重重量', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    firstCost: [
                        {required: true, message: '请输入首重费用', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    continuedCost: [
                        {required: true, message: '请输入续重费用', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    formula: [
                        {required: true, message: '请输入计价公式', trigger: 'blur'}
                    ]
                },
            }
        },
        mounted(){
            let that = this;
            this.$axios.all([this.$axios.get(that.url)])
            .then(this.$axios.spread(function (perms) {
                if(perms.data.code == 200){
                    that.deliveryConfig = perms.data.data;
                }
            }));
        },
        methods: {
            getData: function (){
                let that = this;
                this.$axios.all([this.$axios.get(that.url)])
                .then(this.$axios.spread(function (perms) {
                    if(perms.data.code == 200){
                        that.deliveryConfig = perms.data.data;
                    }
                }));
            },
            editShow: function(){
                let that = this;
                that.editFormVisible = true;
                if (that.$refs.editForm) {
                    that.$refs.editForm.clearValidate();
                }
                that.editForm = Object.assign({}, that.deliveryConfig);
            },
            //编辑
            editSubmit: function () {
                let that = this;
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.editForm);
                        that.$axios.post('boxType/updateDeliveryConfig', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '修改成功', duration: 2000});
                                that.editFormVisible = false;
                                that.getData();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
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