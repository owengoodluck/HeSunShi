<template>
    <div class="container">
        <div class="logo">
            <div class="logo-text">商户logo</div>
            <div class="logo-img">
                <img :src="logo?logo:require('../../assets/logo.png')" style="width:56px;height:56px;">
                <input type="file" class="input-file" @change="uploadLogo($event)" accept="image/gif,image/jpeg,image/jpg,image/png">
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="content">
            <div class="block">
                <span>商户名称：</span>
                <input v-model="name" placeholder="请输入商户名称">
            </div>
            <div class="block">
                <span>主营项目：</span>
                <input v-model="items" placeholder="例如水果、化妆品等">
            </div>
            <div class="block">
                <span>联系人：</span>
                <input v-model="contact" placeholder="请输入真实姓名">
            </div>
            <div class="block" style="border-bottom: 1px solid #FFF;">
                <span>手机号：</span>
                <input v-model="phone" placeholder="请输入手机号码" pattern="[0-9]*">
            </div>
        </div>
        <div style="margin-top: 40px;">
            <button class="save" @click="saveSubmit">保存设置</button>
        </div>
        <div style="margin-top: 20px;">
            <button class="quit" @click="quitSubmit">退出登陆</button>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                id: "",
                logo: "",
                contact: '',
                phone: '',
                name: '',
                items: ''
            }
        },
        created() {
            var that = this;
            this.$axios.get('merchant/selectMerchantDetail')
            .then(function (response) {
                if (response.data.code == 200){
                    that.id = response.data.data.id;
                    that.contact = response.data.data.contact;
                    that.phone = response.data.data.phone;
                    that.name = response.data.data.name;
                    that.items = response.data.data.items;
                    that.logo = response.data.data.logo;
                }
            })
        },
        methods: {
            uploadLogo(e) {
                var that = this;
                let file = e.target.files[0];
                if(file) {
                    var params = new FormData();
                    params.append('file', file, file.name)
                    let config = {
                        headers: {'Content-Type': 'multipart/form-data'}
                    }
                    that.$axios.post('upload/video', params, config)
                    .then(function (response) {
                        that.logo = response.data.data;
                    })
                }
            },
            saveSubmit() {
                var that = this;
                var str = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
                if (this.contact === '') {
                    that.$vux.toast.text("联系人不能为空");
                    return;
                }
                if (this.phone === '' || !str.test(this.phone)) {
                    that.$vux.toast.text("手机号格式不正确或不能为空");
                    return;
                }
                if (this.name === '') {
                    that.$vux.toast.text("商户名称不能为空");
                    return;
                }
                if (this.items === '') {
                    that.$vux.toast.text("主营项目不能为空");
                    return;
                }
                var params = {
                    id: that.id,
                    logo: that.logo,
                    contact: that.contact,
                    phone: that.phone,
                    name: that.name,
                    items: that.items
                }
                that.$axios.post('merchant/update', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$vux.toast.text("保存成功");
                    } else {
                        that.$vux.toast.text(response.data.message);
                    }
                })
            },
            quitSubmit() {
                localStorage.removeItem('Authorization');
                this.$router.push('login');
            }
        }
    }
</script>

<style scoped>
    .container {
        height: 100%;
        background-color: #F5F5F5;
        padding: 10px;
        font-family:PingFangSC-Regular;
        font-weight:400;
    }
    .logo {
        width:100%;
        height:86px;
        background:rgba(255,255,255,1);
        border-radius:10px;
        line-height:86px;
    }
    .logo-text {
        font-size:17px;
        margin-left: 10px;
        float: left;
    }
    .logo-img {
        height:86px;
        display: flex;
        align-items: center;
        float: right;
        margin-right: 17px;
    }
    .content {
        margin-top: 10px;
        padding: 0 10px;
        background: rgba(255,255,255,1);
        border-radius: 8px;
        font-size: 15px;
    }
    .block {
        width: 100%;
        height: 53px;
        border-bottom: 1px solid #E2E0E0;
        display: flex;
        align-items: center;
    }
    input {
        font-size:15px;
        height:36px;
        border:none;
        outline: none;
    }
    ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
        color:#888888;
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color:#888888;
    }
    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color:#888888;
    }
    :-ms-input-placeholder { /* Internet Explorer 10-11 */
        color:#888888;
    }
    .save {
        width: 100%;
        height: 49px;
        background: rgba(255,190,0,1);
        border-radius: 25px;
        border: none;
        outline: none;
        font-size: 15px;
        color: rgba(255,255,255,1);
    }
    .quit {
        width: 100%;
        height: 49px;
        background: #F5F5F5;
        border-radius: 25px;
        outline: none;
        font-size: 15px;
        color: #BBBBBB;
        border:1px solid rgba(187,187,187,1);
    }
    .input-file {
        width: 56px;
        height: 56px;
        position: relative;
        top: 0px;
        left: -56px;
        opacity: 0;
    }
</style>