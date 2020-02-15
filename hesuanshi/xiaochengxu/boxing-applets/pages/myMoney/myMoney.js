const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    money: 0.00
  },
  onLoad: function(options) {
    that = this;
  },
  onShow: function() {
    that.findWallet();
  },
  findWallet: function() {
    network.get('wallet/findWallet', function(res) {
      if (res.data.code == 200) {
        that.setData({
          money: res.data.data.money
        })
      }
    })
  },
  rechargeTap: function() {
    wx.navigateTo({
      url: '/pages/recharge/recharge'
    })
  },
  mingxiTap: function() {
    wx.navigateTo({
      url: '/pages/moneyMingxi/moneyMingxi'
    })
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})