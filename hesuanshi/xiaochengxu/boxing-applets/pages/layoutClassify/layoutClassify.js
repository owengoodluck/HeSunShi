const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    source: 1,
    boxTypeDetailId: 0,
    windowWidth: 0,
    currentIndex: 0,
    classifyList: []
  },
  onLoad: function (options) {
    that = this;
    that.setData({
      source: options.source,
      boxTypeDetailId: options.boxTypeDetailId,
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function () {
    this.queryClassify();
  },
  queryClassify: function () {
    network.get("boxLayout/queryClassify?boxTypeDetailId=" + this.data.boxTypeDetailId, function (res) {
      if (res.data.code === 200) {
        that.setData({
          classifyList: res.data.data
        })
      }
    })
  },
  chooseDetail: function (e) {
    this.setData({
      currentIndex: e.currentTarget.dataset.index
    })
  },
  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/layoutDetail/layoutDetail?id=' + e.currentTarget.dataset.id + '&source=' + this.data.source + '&type=' + this.data.type
    })
  },
  chooseImage: function () {
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success(res) {
        var tempFilePaths = res.tempFilePaths
        wx.uploadFile({
          url: network.serverUrl + 'upload/video',
          filePath: tempFilePaths[0],
          name: 'file',
          success: function (res) {
            app.globalData.calculated.layoutId = 0;
            app.globalData.calculated.layoutName = "自有版面";
            app.globalData.calculated.layoutImage = JSON.parse(res.data).data;

            if (that.data.source == 1) {
              wx.switchTab({
                url: '/pages/home/home'
              })
            } else {
              if (app.globalData.calculated.typeId == 0 || app.globalData.calculated.materialId == 0) {
                wx.redirectTo({
                  url: '/pages/calculated/calculated?isShow=0'
                })
              } else {
                wx.redirectTo({
                  url: '/pages/calculated/calculated?isShow=1'
                })
              }
            }
          },
          fail: function (err) {
            console.log(err)
          }
        })
      }
    })
  },
  onShareAppMessage: function () {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})