<template>
    <div class="login-wrap">
        <div class="ms-title">创无纸境管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="account">
                    <el-input v-model="ruleForm.account" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="passWord">
                    <el-input type="password" placeholder="请输入密码" v-model="ruleForm.passWord" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                ruleForm: {
                    account: '',
                    passWord: ''
                },
                rules: {
                    account: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    passWord: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                const that = this;
                that.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, that.ruleForm);
                        that.$axios.post('sysUser/login', params)
                        .then(function (response) {
							if (response.data.code == 200){
                                localStorage.setItem("Authorization", response.data.data.token_type + response.data.data.access_token);
                                localStorage.setItem("account", that.ruleForm.account);
                                localStorage.setItem("userName", response.data.data.sysUserName);
                                localStorage.setItem("imageUrl", response.data.data.imageUrl);
                                localStorage.setItem("permissions", response.data.data.permissions.split(","));
                                that.$router.push({
                                    path: that.$route.query.redirect || '/readme'
                                });
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:300px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
</style>