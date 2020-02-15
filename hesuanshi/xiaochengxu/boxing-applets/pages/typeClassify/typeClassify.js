const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    id: 0,
    source: 1,
    windowWidth: 0,
    currentIndex: 0,
    classifyList: [],
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      source: options.source,
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function() {
    this.queryClassify();
  },
  queryClassify: function() {
    network.get("boxType/queryClassifyDetailAll", function(res) {
      if (res.data.code === 200) {
        that.setData({
          classifyList: res.data.data
        })
        app.globalData.calculated.classifyList = res.data.data;
      }
    })
  },
  chooseDetail: function(e) {
    this.setData({
      currentIndex: e.currentTarget.dataset.index
    })
  },
  gotoDetail: function(e) {
    wx.navigateTo({
      url: '/pages/typeDetail/typeDetail?id=' + e.currentTarget.dataset.id + '&source=' + this.data.source + '&type=' + this.data.type
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
            app.globalData.calculated.typeId = 0;
            app.globalData.calculated.typeName = "自有盒型";
            app.globalData.calculated.typeImage = JSON.parse(res.data).data;
            app.globalData.calculated.homeImagesCard = [JSON.parse(res.data).data];

            app.globalData.calculated.materialId = 0;
            app.globalData.calculated.materialName = "选择材质";
            app.globalData.calculated.materialImage = "/images/home_default.png";

            app.globalData.calculated.layoutId = 0;
            app.globalData.calculated.layoutName = "选择版面";
            app.globalData.calculated.layoutImage = "/images/home_default.png";

            app.globalData.calculated.printMethodId = 0;
            app.globalData.calculated.printMethodName = '选择印刷方式';

            if (that.data.source == 1) {
              wx.switchTab({
                url: '/pages/home/home'
              })
            } else {
              wx.redirectTo({
                url: '/pages/calculated/calculated?isShow=0'
              })
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