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
  onShow: function() {},
  bindMoneyInput: function(e) {
    that.setData({
      money: e.detail.value
    })
  },
  rechargeTap: function() {
    if (that.data.money <= 0) {
      wx.showToast({
        icon: 'none',
        title: '充值金额不能小于等于0',
        duration: 1500
      })
      return;
    } else {
      var params = {
        money: that.data.money
      }
      network.post('wallet/recharge', params, function (res) {
        if (res.data.code == 200) {
          wx.requestPayment({
            timeStamp: res.data.data.timestamp,
            nonceStr: res.data.data.nonce_str,
            package: res.data.data.extended,
            signType: "MD5",
            paySign: res.data.data.sign,
            success: function (e) {
              var notifyParams = {
                outTradeNo: res.data.data.outTradeNo
              };
              network.post("pay/orderNotify", notifyParams, function (res) {
                if (res.data.code == 200) {
                  wx.showToast({
                    title: "支付成功"
                  });
                  wx.navigateBack({})
                } else {
                  wx.showToast({
                    title: "支付失败"
                  });
                }
              });
            },
            fail: function (e) {
              wx.showToast({
                title: "支付取消"
              });
            }
          });
        }
      })


    }
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})