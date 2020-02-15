const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    windowWidth: 0,
    canCommission: 0
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      windowWidth: app.globalData.windowWidth,
      canCommission: app.globalData.canCommission
    })
  },
  onShow: function() {

  },
  tixianTap: function(e) {
    if (that.data.canCommission <= 0) {
      wx.showToast({
        icon: 'none',
        title: "提现金额不能小于等于0",
        duration: 1500
      })
    } else {
      var params = {
        withdrawableMoney: that.data.canCommission,
        formId: e.detail.formId
      }
      network.post('promoter/withdrawableAll', params, function(res) {
        if (res.data.code == 200) {
          wx.showToast({
            title: '您的提现已提交,请等待客服处理...',
            icon: 'none',
            duration: 1500,
            mask: true,
            success: function() {
              setTimeout(function() {
                //要延时执行的代码
                wx.switchTab({
                  url: '/pages/my/my'
                });
              }, 2000) //延迟时间
            },
          });
        }
      })
    }

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