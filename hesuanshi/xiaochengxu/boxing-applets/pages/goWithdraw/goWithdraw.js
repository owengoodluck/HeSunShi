const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    windowWidth: 0,
    canCommission: 0,
    pendingCommission: 0
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      windowWidth: app.globalData.windowWidth,
      canCommission: app.globalData.canCommission,
      pendingCommission: app.globalData.pendingCommission
    })
  },
  onShow: function() {

  },
  tixianTap: function() {
    if (that.data.canCommission <= 0) {
      wx.showToast({
        icon: 'none',
        title: "当前没有可提现额度",
        duration: 1500
      })
    } else {
      app.globalData.canCommission = that.data.canCommission;
      wx.navigateTo({
        url: '/pages/withdraw/withdraw'
      })
    }

  },
  goIncomeAndExpensesTap: function() {
    wx.navigateTo({
      url: '/pages/incomeAndExpenses/incomeAndExpenses'
    })
  },
  goWithdrawRecordTap: function() {
    wx.navigateTo({
      url: '/pages/withdrawRecord/withdrawRecord'
    })
  },
  //下拉刷新
  onPullDownRefresh: function() {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }

})