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
    network.get("boxLayout/queryClassifyDetailById?id=" + this.data.id, function (res) {
      if (res.data.code === 200) {
        that.setData({
          detail: res.data.data
        })
      }
    })
  },
  certainTap: function () {
    app.globalData.calculated.layoutId = this.data.detail.id;
    app.globalData.calculated.layoutName = this.data.detail.name;
    app.globalData.calculated.layoutImage = this.data.detail.image;
    if (app.globalData.calculated.homeImagesCard.length == 2){
      app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([this.data.detail.image]);
    }else{
      app.globalData.calculated.homeImagesCard.splice(2,1);
      app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([this.data.detail.image]);
    }
    if (this.data.source == 1) {
      wx.switchTab({
        url: '/pages/home/home'
      })
    } else {
      if (app.globalData.calculated.typeId == 0 || app.globalData.calculated.materialId == 0) {
        wx.redirectTo({
          //url: '/pages/calculated/calculated?isShow=0'
          url: '/pages/programDetail/programDetail?isShow=0'
        })
      } else {
        wx.redirectTo({
          //url: '/pages/calculated/calculated?isShow=1'
          url: '/pages/programDetail/programDetail?isShow=1'
        })
      }
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