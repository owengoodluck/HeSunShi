<template>
    <div class="main">
        <div style="width: 876px;margin-top: 64px;">
            <div v-if="listData.length > 0">
                <div style="font-size:20px;font-family:PingFangSC-Regular;font-weight:400;color:rgba(119,133,164,1);">
                    资源文件：
                </div>
                <div v-for="(item,index) in listData">
                    <div class="item">
                        <div class="fileName">
                            <span style="color: #333">{{index+1}}</span>
                            <span style="margin-left: 30px;">{{item.name}}</span>
                        </div>
                        <div class="caozuo">
                            <span v-if="item.name.split('.')[1] != 'zip' && item.name.split('.')[1] != 'rar'" style="margin-right: 20px;" @click="lookBigIcon(item.file)">预览</span>
                            <a :href='item.file' download="" target="_blank" title="下载">下载</a>
                        </div>
                        <div style="clear:both;"></div>
                    </div>
                </div>
            </div>
            <div v-if="listData.length == 0" class="noData">暂无数据</div>
        </div>
        <el-dialog :visible.sync ="showPhotoVisible" :close-on-click-modal="false" width="40%">
            <img :src="photo" class="head_pic" style="max-width:100%;max-height:100%;"/>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                type: 0,
                number: 0,
                listData: [],
                showPhotoVisible: false,
                photo: ""
            }
        },
        created: function() {
            var that = this;
            this.type = this.$route.params.type;
            this.number = this.$route.params.number;
            that.$axios.get('resource/findResourceByNumber/' + that.number)
            .then(function (res) {
                if (res.data.code == 200){
                    that.listData = res.data.data;
                } 
            });
        },
        methods: {
            //点击查看大图片
            lookBigIcon: function (file) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = file;
            },
           
        }
    }
</script>

<style scoped>
    .main {
        display: flex;
        justify-content: center;
        height: 100%;
        background: #E9EBF0;
    }
    .item {
        height:70px;
        background:rgba(255,255,255,1);
        box-shadow:0px 0px 16px 0px rgba(223,228,239,1);
        border-radius:10px;
        margin-top: 20px;
        line-height: 70px;
    }
    .fileName {
        margin-left: 20px;
        float: left;
        font-size:17px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(119,133,164,1);
    }
    .caozuo {
        margin-right: 34px;
        float: right;
        font-size:17px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(119,133,164,1);
    }
    .noData {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 70px;
        font-size: 20px;
        font-family:PingFangSC-Regular;
        font-weight:400;
        color:rgba(119,133,164,1);
    }

</style>