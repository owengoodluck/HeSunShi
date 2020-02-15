<template>
  <div>
    <script id="editorEdit" type="text/plain" ></script>
  </div>
</template>

<script>
  import '../../../static/ueditor/ueditor.config.js'
  import '../../../static/ueditor/ueditor.all.js'
  import '../../../static/ueditor/lang/zh-cn/zh-cn.js'
  import '../../../static/ueditor/jquery-2.2.3.min.js'

  export default {
    name: "UEditorEdit",
    props: {
      id: {
          type: String
      },
      config: {
          type: Object
      }
    },
    data() {
      return {
        editorEdit: null
      }
    },
    mounted() {
      //初始化UE
      const _this = this;
      this.editorEdit = UE.delEditor("editorEdit");
      this.editorEdit = UE.getEditor('editorEdit',this.config);
    },
    destoryed() {
      this.editorEdit.destory();
    },
    methods:{
      getUEContent: function(){
       return this.editorEdit.getContent();
      },
      setUEContent: function(content){
       return this.editorEdit.setContent(content);
      }
    }
  }
</script>
