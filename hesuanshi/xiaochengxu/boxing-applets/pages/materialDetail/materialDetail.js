const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    id: '',
    source: 1,
    detail: {}
  },
  onLoad: function (options) {
    that = this;
    that.setData({
      id: options.id,
      source: options.source,
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function () {
    this.queryClassifyDetail();
  },
  queryClassifyDetail: function () {
    network.get("boxMaterial/queryClassifyDetailById?id=" + this.data.id, function (res) {
      if (res.data.code === 200) {
        that.setData({
          detail: res.data.data
        })
      }
    })
  },
  certainTap: function () {
    app.globalData.calculated.materialId = this.data.detail.id
    app.globalData.calculated.materialName = this.data.detail.name;
    app.globalData.calculated.materialImage = this.data.detail.image;
    console.log("所选的材质==>" + this.data.detail.image);
    console.log("选择前=>"+app.globalData.calculated.homeImagesCard);
    app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([this.data.detail.image]);
    console.log("选择后=>" + app.globalData.calculated.homeImagesCard);

    if (this.data.source == 1) {
      wx.switchTab({
        url: '/pages/home/home'
      })
    } else {
      wx.redirectTo({
        url: '/pages/calculated/calculated?isShow=1'
      })
    }
  },
  onShareAppMessage: function () {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})