<template>
    <div class="main">
        <el-row>
          <el-col :span="24"><div class="title">创无纸镜管理系统</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>  
          <el-col :span="8">
            <div class="grid-content bg-purple-light notice">
              <div class="notice-title">尊敬的客户</div>
              <div class="notice-content">您好，欢迎使用创无止境管理系统。您现在使用的是上传设计图功能。请按以下提示完成方案上传:</div>
              <div class="notice-content"></div>
              <div class="notice-line"></div>
              <div class="notice-content">方案编号：{{customSpecification.customNumber}}</div>
              <div class="notice-content">方案名称：{{customSpecification.name}}</div>
              <div class="notice-content">方案金额：{{customSpecification.price}}</div>
            </div>
          </el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>

        <el-row v-show="!canUpload">
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>  
          <el-col :span="8">
           <div class="notice3">找不到方案，请检查打开的地址是否正确；</div>
          </el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
        
        <el-row v-show="canUpload">
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>  
          <el-col :span="8">
           <div class="notice2">依次上传平面图、源文件、效果图；</div>
          </el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>

        <!-- 平面图 -->
        <el-row v-show="canUpload">
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light ">                               
                    <div style="text-align: center;" class="notice1" >
                        <el-upload
                            
                            list-type="picture"
                            :on-preview="imagePreview"
                            :file-list="imageList"
                            :action="action1"
                            :before-upload="imageBeforeUpload"
                            :on-remove="onImageRemove"
                            :limit="limit"
                            :on-success="imageSuccess"
                            :on-exceed="handleExceed">
                            <el-button size="small" type="primary" v-show="imageIsUploaded">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">第一步，上传平面图，仅支持JPG，PNG格式的图片，且不超过20M</div>                                                      
                            
                        </el-upload>
                        
                    </div>
            </div>
          </el-col>
           <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
        <!-- 源文件 -->
        <el-row v-show="canUpload">
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light ">
            
                <div style="text-align: center;" class="notice1">
                    <el-upload
                        
                        
                        list-type="picture"
                        :file-list="fileList"
                        :action="action2"
                        :before-upload="fileBeforeUpload"
                        :on-remove="onFileRemove"
                        :limit="limit"
                        :on-success="fileSuccess"
                        :on-exceed="handleExceed">
                        <el-button size="small" type="primary" v-show="fileIsUploaded">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">第二步：上传源文件，仅支持PSD、Ai、CDR、PDF格式，且不超过500M</div>
                        
                    </el-upload>

                </div>
            </div>
            
        </el-col>
        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
        <!-- 效果图 -->
        <el-row v-show="canUpload">
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light ">
            
                <div style="text-align: center;" class="notice1">
                    <el-upload
                        
                        list-type="picture"
                        :on-preview="imagePreview"
                        :file-list="fileList2"
                        :action="action3"
                        :before-upload="imageBeforeUpload2"
                        :on-remove="onFileRemove2"
                        :limit="limit"
                        :on-success="fileSuccess2"
                        :on-exceed="handleExceed">
                        <el-button size="small" type="primary" v-show="fileIsUploaded2">点击上传</el-button>

                            <div slot="tip" class="el-upload__tip">第三步：上传效果图，仅支持JPG、PNG格式，且不好过20M</div>
                        
                    </el-upload>
                </div>
            </div>
          </el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>      
        </el-row>
        
        <el-row v-show="canUpload">
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light ">            
                <el-button type="primary" @click="submitUpload">确认上传</el-button>
            </div>
          </el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>  
        </el-row>
        
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                type: 0,
                number: 0,
                action1: '',
                action2: '',
                action3: '',
                limit: 1,
                customSpecification:{},
                canUpload:true,
                imageIsUploaded:true,
                fileIsUploaded:true,
                fileIsUploaded2:true,
                resourceFiles:{},
                dialogImageUrl: '',
                dialogVisible: false,
                imageList:[],
                fileList:[],
                fileList2:[]
     
            }
        },
        created: function() {
            //this.type = this.$route.params.type;
            this.number = this.$route.params.number;
            this.action1 = this.$axios.defaults.baseURL + '/upload/resource/1/' + this.number;
            this.action2 = this.$axios.defaults.baseURL + '/upload/resource/2/' + this.number;
            this.action3 = this.$axios.defaults.baseURL + '/upload/resource/3/' + this.number;
        },
        mounted(){
            this.findCustomSpecification();
            this.findResourceFiles();
        },
        methods: {
            findResourceFiles:function(){
                let that = this;
                let params = {
                }

                that.$axios.get('resource/findResourceByNumber/'+that.number, params)
                    .then(function (response) {
                        if (response.data.code == 200){                            
                            that.resourceFiles = response.data.data;
                            for(var i = 0; i < that.resourceFiles.length; i ++){
                                if(that.resourceFiles[i].type == '1'){
                                  that.image = that.resourceFiles[i];
                                  var item = {
                                    name:that.image.name,
                                    url:that.image.file
                                  }
                                  that.imageList.push(item);
                                  that.imageIsUploaded = false;
                                }
                                if(that.resourceFiles[i].type == '2'){
                                  that.file = that.resourceFiles[i];
                                  var item = {
                                    name:that.file.name,
                                    url:that.file.file
                                  }
                                  that.fileList.push(item);
                                  that.fileIsUploaded = false;
                                }
                                if(that.resourceFiles[i].type == '3'){
                                  that.file2 = that.resourceFiles[i];
                                  var item = {
                                    name:that.file2.name,
                                    url:that.file2.file
                                  }
                                  that.fileList2.push(item);
                                  that.fileIsUploaded2 = false;
                                }
                            }
                            return true;
                        }
                });
            },
            findCustomSpecification:function(){
                let that = this;
                let params = {
                    customNumber: that.number
                }
                that.$axios.post('customSpecification/queryByCustomNumber', params)
                    .then(function (response) {
                        console.log(response.data.code);
                        if (response.data.code == 200){
                            that.canUpload = true;
                            that.customSpecification = response.data.data;
                            return true;
                        }else{
                            that.canUpload = false;
                        }
                });
            },
            handleExceed: function(fileList){
                this.$message.warning(`当前限制只能上传1个文件`);
            },
            //上传文件规则
            fileBeforeUpload: function (file) {
                console.log("file==>"+file);
                var isPSD = file.name.split(".")[1];
                console.log("isPSD==>"+isPSD);
                if(!(isPSD.toUpperCase() === 'PSD' || isPSD.toUpperCase() === 'AI' || isPSD.toUpperCase() === 'CDR' || isPSD.toUpperCase() === 'PDF')){
                    this.$message.error('只能上传psd,ai,cdr,pdf四种格式文件文件');
                    return false;
                }
                const size = file.size / 1024 / 1024 < 500;
                if (!size) {
                  this.$message.error('上传文件大小不能超过 500MB!');
                }
                return size;
            },

            //上传文件规则
            imageBeforeUpload: function (file) {
                const size = file.size / 1024 / 1024 < 20;
                const isJPG = file.type === 'image/jpeg';
                const isGIF = file.type === 'image/gif';
                const isPNG = file.type === 'image/png';
                if(!isPNG && !isGIF && !isJPG){
                    this.$message.error('上传图片格式不正确');
                    return false;
                }
                if (!size) {
                  this.$message.error('上传图片大小不能超过 20MB!');
                }
                return size;
            },
            imageBeforeUpload2: function (file) {
                const size = file.size / 1024 / 1024 < 20;
                const isJPG = file.type === 'image/jpeg';
                const isGIF = file.type === 'image/gif';
                const isPNG = file.type === 'image/png';
                if(!isPNG && !isGIF && !isJPG){
                    this.$message.error('上传图片格式不正确');
                    return false;
                }
                if (!size) {
                  this.$message.error('上传图片大小不能超过 20MB!');
                }
                return size;
            },
            onImageRemove: function (file, fileList) {
                if(file.status != "ready"){
                    let that = this;
                    let params = {
                        resourceNumber: that.number,
                        file: that.imageList[0].url
                        //file: file.response.data
                    }
                    that.$axios.post('resource/delete', params)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.imageIsUploaded = true;
                            return true;
                        } 
                    });  
                }
            },
            
            onFileRemove: function (file, fileList) {
                if(file.status != "ready"){
                    let that = this;
                    let params = {
                        resourceNumber: that.number,
                        file: that.fileList[0].url
                        //file: file.response.data
                    }
                    that.$axios.post('resource/delete', params)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.fileIsUploaded == true
                            return true;
                        } 
                    });  
                }
            },
            onFileRemove2: function (file, fileList) {
                if(file.status != "ready"){
                    let that = this;
                    let params = {
                        resourceNumber: that.number,
                        file: that.fileList2[0].url
                        //file: file.response.data
                    }
                    that.$axios.post('resource/delete', params)
                    .then(function (response) {
                        if (response.data.code == 200){
                            that.$message.success({showClose: true, message: '删除成功', duration: 2000});
                            that.fileIsUploaded2 = true;
                            return true;
                        } 
                    });  
                }
            },
            imagePreview:function(file){
               this.dialogImageUrl = file.url;
               this.dialogVisible = true;
            },

            imageSuccess:function(res, file){
              this.imageList = [];
              var item = {
                name:file.name,
                url:file.response.data
              };
              this.imageList.push(item);
              this.imageIsUploaded = false;
            },
            fileSuccess:function(res, file){
              this.fileList = [];
              var item = {
                name:file.name,
                url:file.response.data
              };
              this.fileList.push(item);
              this.fileIsUploaded = false;
            },
            fileSuccess2:function(res, file){
              this.fileList2 = [];
              var item = {
                name:file.name,
                url:file.response.data
              };
              this.fileList2.push(item);  
              this.fileIsUploaded2 =  false;
            },
            submitUpload:function(){
              let that = this;
              if(this.imageIsUploaded == true){
                that.$message.error('请上传平面图');
                return;
              }
              if(this.fileIsUploaded == true){
                that.$message.error('请上传源文件');
                return;
              }
              if(this.fileIsUploaded2 == true){
                that.$message.error('请上传效果图');
                return;
              }
              
              /**
               * 执行提交
               */
              let params = {
                customNumber: that.number
              }
              that.$axios.post('customSpecification/isUpload', params)
                    .then(function (response) {
                        console.log(response.data.code);
                        if (response.data.code == 200){
                            that.$message.success('已完成上传');
                            return true;
                        }else{
                            that.$message.error('上传失败');
                        }
              });
            }
        }
    }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    
  }
  .bg-purple {
    
  }
  .bg-purple-light {
    
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
    .main {
        
        text-align:center;
        justify-content: center;
        align-items: center;
        height: 100%;
        
        background-repeat: repeat;
        -webkit-background-size: cover;
        -o-background-size: cover;
        background-color:#f8f8f8;
        overflow:auto;
    }
    .notice{
        border:1px solid #e6e6e6;
        border-radius:18px;
        min-height:150px;
        width:100%;
        text-align:left;
        font-family:"Helvetica Neue",Helvetica, arial, STHeiTi, sans-seri;
        background-color:#ffffff;
    }
    .notice2{
        font-family:"Helvetica Neue",Helvetica, arial, STHeiTi, sans-seri;
        text-align:left;
        margin-top:20px;
        margin-left:20px;
    }
    .notice3{
        font-family:"Helvetica Neue",Helvetica, arial, STHeiTi, sans-seri;
        text-align:left;
        margin-top:20px;
        color:red;
        margin-left:20px;
    }
    .notice-title{
        margin-left:20px;
        margin-top:20px;

    }
    .notice-content{
        margin-left:50px;
        margin-top:20px;
    }
    .notice-line{
        border:1px solid #e6e6e6;
        margin-top : 20px;
        margin-bottom : 20px;
        margin-left:20px;
        margin-right:20px;
    }
    .my-row{
        text-align:center;
    }
    .title {
        text-align:center;
        font-size: 24px;
        font-weight:800;
        font-family:"Helvetica Neue",Helvetica, arial, STHeiTi, sans-seri;
        margin-top:78px;
    }
    .notice1{
        vertical-align: middle;
        border:1px dotted #fc4c52;
        border-radius:18px;
        height:188px;
        width:100%;
        text-align:left;
        font-family:"Helvetica Neue",Helvetica, arial, STHeiTi, sans-seri;
        background-color:#ffffff;
    }
    
    .el-button{
        width:100%;
    }
    .image{
        position: absolute;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .image img{
        width:200px;
    }
</style>