<template>
    <div class="container">
        <div class="borderView">
            <div class="main-block">
                <div class="table-text">姓名:</div>
                <input v-model="contact" placeholder="请输入店员名称" class="input">
            </div>
            <div class="main-block">
                <div class="table-text">手机号:</div>
                <input v-model="phone" placeholder="请填写手机号码" class="input" pattern="[0-9]*">
            </div>
            <div style="margin-top: 30px;">
                <button class="add" @click="addSubmit">确定</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                contact: "",
                phone: ""
            }
        },
        created() {
            console.log('created')
        },
        methods: {
            addSubmit: function(){
                var that = this;
                var str = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
                if (that.contact === '') {
                    that.$vux.toast.text("联系人不能为空");
                    return;
                }
                if (that.phone === '' || !str.test(that.phone)) {
                    that.$vux.toast.text("手机号格式不正确或不能为空");
                    return;
                }
                var params = {
                    contact: that.contact,
                    phone: that.phone
                }
                that.$axios.post('merchant/insertStaff', params)
                .then(function (response) {
                    if (response.data.code == 200){
                        that.$vux.toast.text("新增成功");
                        that.$router.go(-1);//返回上一页
                    } else {
                        that.$vux.toast.text(response.data.message);
                    }
                })
            }  
        }
    }
</script>

<style scoped>
    .container {
        padding: 24px;
        height: 100%;
        background: #f5f5f5;
    }
    .borderView {
        background: #fff;
        border-radius:10px;
        padding: 30px 20px;
    }
    .main-block {
        height: 45px;
        border-bottom: 1px solid #f0f0f0;
        display: flex;
        align-items: center;
    }
    .table-text {
        width: 55px;
        font-size: 14px;
    }
    .input {
        font-size: 14px;
        margin-left: 6px;
        height: 36px;
        border: none;
        outline: none;
    }
    .add {
        width: 100%;
        height: 45px;
        background: rgba(255,190,0,1);
        border-radius: 23px;
        border: none;
        outline: none;
        font-size: 15px;
        color: rgba(255,255,255,1);
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
</style>