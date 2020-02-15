const app = getApp();

Component({
  properties: {},
  data: {
    showTolast: !1,
    name: ""
  },
  methods: {
    toggleToast: function() {
      var t = this.data.showTolast;
      this.setData({
        showTolast: !t
      });
    },
    preD: function () { },
    inputName: function(e) {
      this.setData({
        name: e.detail.value
      })
    },
    sendExchangeCode: function() {
      if (this.data.name === '') {
        wx.showToast({
          title: "请输入方案名称",
          icon: "none"
        })
      } else {
        app.globalData.calculated.name = this.data.name;
        this.toggleToast();
        this.triggerEvent("cancelEvent");
      }
    }
  }
});