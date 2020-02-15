const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    currentIndex: 0,
    windowWidth: 0,
    windowHeight: 0,
    shareData: []
  },
  onLoad: function (options) {
    that = this;
    this.setData({
      windowWidth: app.globalData.windowWidth,
      windowHeight: app.globalData.windowHeight
    })
  },
  onShow: function () {
    this.queryList();
  },
  queryList: function () {
    network.get("poster/queryList", function (res) {
      if (res.data.code === 200) {
        var shareData = [];
        for(var i=0; i < res.data.data.length;i++) {
          shareData.push(network.serverUrl + 'auth/share/' + res.data.data[i].id + '?userId=' + wx.getStorageSync("userId"));
        }
        that.setData({
          shareData: shareData
        })
      }
    })
  },
  previewImage: function (e) {
    wx.previewImage({ 
      current: that.data.shareData[that.data.currentIndex],  	
      urls: that.data.shareData
    })
  },
  showInviteBg: function (e) {
    var currentIndex = e.target.dataset.index;
    if (currentIndex != this.data.currentIndex) {
      this.setData({
        currentIndex: currentIndex
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