const app = getApp();

Component({
  properties: {
  },
  data: {
    showTolast: !1,
    name: ""
  },
  methods: {
    toggleToast: function () {
      var t = this.data.showTolast;
      this.setData({
        showTolast: !t
      });
    },
    sendProject: function () {
      if (this.data.name === '') {
        wx.showToast({
          title: "请输入方案名称",
          icon: "none"
        })
      } else {
        app.globalData.calculated.name = this.data.name;
        this.toggleToast();
        this.triggerEvent("cancelAddProject");
      }
    },
    toShejigao:function(){
      console.log('toShejigao');
      wx.navigateTo({
        url: '/pages/design/design'
      })
    }
  }
})
