<template>
  <div>
    <script id="editorAdd" type="text/plain" ></script>
  </div>
</template>

<script>
  import '../../../static/ueditor/ueditor.config.js'
  import '../../../static/ueditor/ueditor.all.js'
  import '../../../static/ueditor/lang/zh-cn/zh-cn.js'
  import '../../../static/ueditor/jquery-2.2.3.min.js'

  export default {
    name: "UEditorAdd",
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
        editorAdd: null
      }
    },
    mounted() {
      //初始化UE
      const _this = this;
      this.editorAdd = UE.delEditor("editorAdd");
      this.editorAdd = UE.getEditor('editorAdd',this.config);
    },
    destoryed() {
      this.editorAdd.destory();
    },
    methods:{
      getUEContent: function(){
       return this.editorAdd.getContent();
      },
      setUEContent: function(content){
       return this.editorAdd.setContent(content);
      }
    }
  }
</script>
