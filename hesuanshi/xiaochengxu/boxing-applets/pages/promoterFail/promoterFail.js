const app = getApp();
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

  },
  homeTap:function(){
    wx.switchTab({
      url: '/pages/home/home'
    })
  },
  registerAgainTap: function() {
    wx.redirectTo({
      url: '/pages/registerPromoter/registerPromoter'
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