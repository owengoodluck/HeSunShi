const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    status: 0,
    windowWidth: 0
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      windowWidth: app.globalData.windowWidth
    });
  },
  onShow: function() {
    that.findPromoterByUserId();
  },
  goHomeTap: function() {
    wx.switchTab({
      url: '/pages/home/home'
    });
  },
  //推广会员
  findPromoterByUserId: function() {
    network.get('promoter/findPromoterByUserId', function(res) {
      if (res.data.code == 200) {
        that.setData({
          status: res.data.data.status
        })
      }
    })
  },
  homeTap:function(){
    wx.switchTab({
      url: '/pages/home/home'
    })
  },
  registerAgainTap: function() {
    wx.navigateTo({
      url: '/pages/registerPromoter/registerPromoter'
    });
  },
  shareTap: function() {
    network.get('/promoter/addNumber', function(res) {})
  },
  createCodeTap: function() {
    network.get('/promoter/addNumber', function(res) {})
    wx.navigateTo({
      url: '/pages/preview/preview'
    });
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})