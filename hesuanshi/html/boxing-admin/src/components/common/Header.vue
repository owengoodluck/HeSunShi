<template>
    <div class="header">
        <div class="logo">创无纸境管理系统</div>
        <div class="user-info">
            <el-dropdown trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img class="user-logo" src="../../assets/logo.png">
                    {{username}}
                </span>
                <el-dropdown-menu slot="dropdown">
                	<!-- <el-dropdown-item command="upPassword">修改密码</el-dropdown-item> -->
                    <el-dropdown-item command="loginout">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
        
        <!--修改密码界面-->
        <el-dialog title="修改密码" :visible.sync ="passVisible" :close-on-click-modal="false">
            <el-form :model="passForm" status-icon label-width="80px" :rules="passFormRules" ref="passForm">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="旧密码" prop="oldPassword">
                            <el-input type="password" v-model="passForm.oldPassword" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="新密码" prop="newPassword">
                            <el-input type="password" v-model="passForm.newPassword" clearable autofocus></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="确认密码" prop="morePass">
                            <el-input type="password" v-model="passForm.morePass" clearable auto-complete="off"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="passVisible = false">取消</el-button>
                <el-button type="primary" @click.native="passSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
        
    </div>
</template>
<script>
    export default {
        data() {
        	var checkAge = (rule, value, callback) => {
        		if (value === '') {
		          callback(new Error('请再次输入密码'));
		        } else if (value !== this.passForm.newPassword) {
		          callback(new Error('两次输入密码不一致!'));
		        } else {
		          callback();
		        }	
        	};
        	
            return {
            	passVisible: false,
            	addLoading: false,
            	passForm: {
            		oldPassword: '',
            		newPassword: '',
            		morePass: '',
            		userId: ''
            	},
            	passFormRules: {
                    oldPassword: [
                        {required: true, message: '请输入旧密码', trigger: 'blur'}
                    ],
                    newPassword: [
                        {required: true, message: '请输入新密码', trigger: 'blur'}
                    ],
                    morePass: [
                        { validator: checkAge, trigger: 'blur'}
                    ]
                },
                name: '创无纸境'
            }
        },
        computed:{
            username(){
                // let username = localStorage.getItem('userName');
                // return username ? username : this.name;
                let username = localStorage.getItem('userName');
                return username ? username : localStorage.getItem('account');
            }
        },
        methods:{
            handleCommand(command) {
                if(command == 'loginout'){
                    //localStorage.clear();
                    localStorage.removeItem('userName');
                    localStorage.removeItem('Authorization');
                    localStorage.removeItem('permissions');
                    
                    this.$router.push('/login');
                }else if(command == 'upPassword'){
                	this.passVisible = true;
                }
            },
            // 修改密码
        	passSubmit:function(){
        		
        		let that = this;
        		that.passForm.userId = localStorage.getItem('userId');
                that.$axios.post('auth/alterPassword', that.passForm)
                .then(function (response) {
                	console.log(response);
                    if (response.data.code == 200){
                        
                        that.$message.success({showClose: true, message: '成功', duration: 2000});
                        setTimeout(function(){
                        	
	                        window.location.reload();
	                    },1500);
                        
                    } else {
                        that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                    }
                });
        		
        	}
        }
    }
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        line-height: 70px;
        color: #fff;
    }
    .header .logo{
        float: left;
        width:250px;
        text-align: center;
    }
    .user-info {
        float: right;
        padding-right: 50px;
        font-size: 16px;
        color: #fff;
    }
    .user-info .el-dropdown-link{
        position: relative;
        display: inline-block;
        padding-left: 50px;
        color: #fff;
        cursor: pointer;
        vertical-align: middle;
    }
    .user-info .user-logo{
        position: absolute;
        left:0;
        top:15px;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>
