const app = getApp();

var network = require('../../utils/network.js');
var that;
Page({
  data: {
    outTradeNo: '',
    orderDetail: {},
    yunfei: 0.00,
    count: 0.00
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      outTradeNo: options.outTradeNo || that.data.outTradeNo
    })
  },
  onShow: function() {
    that.findOrderDetailByOutTradeNo();
  },
  //根据微信支付订单号查询订单详情
  findOrderDetailByOutTradeNo: function() {
    network.get('order/findOrderDetailByOutTradeNo/' + that.data.outTradeNo, function(res) {
      if (res.data.code == 200) {
        var list = res.data.data.orderDetailVO
        var yunfei = 0.00;
        var count = 0.00;
        for (var i = 0; i < list.length; i++) {
          yunfei = that.accAdd(yunfei, list[i].fare)
          count = that.accAdd(count, list[i].price)
        }
        that.setData({
          orderDetail: res.data.data,
          yunfei: yunfei,
          count: count
        })
      }
    })
  },
  //退款
  tuikuanTap: function(e) {
    //退款页面
    app.globalData.orderDetailVO = that.data.orderDetail.orderDetailVO[e.currentTarget.dataset.index];
    app.globalData.fareType = that.data.orderDetail.fareType;
    wx.navigateTo({
      url: '/pages/refundSub/refundSub'
    })
  },
  //下拉刷新
  onPullDownRefresh: function() {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  onShareAppMessage: function () {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  },
  //加法运算
  accAdd: function(arg1, arg2) {
    var r1, r2, m, c;
    try {
      r1 = arg1.toString().split(".")[1].length;
    } catch (e) {
      r1 = 0;
    }
    try {
      r2 = arg2.toString().split(".")[1].length;
    } catch (e) {
      r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
      var cm = Math.pow(10, c);
      if (r1 > r2) {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", "")) * cm;
      } else {
        arg1 = Number(arg1.toString().replace(".", "")) * cm;
        arg2 = Number(arg2.toString().replace(".", ""));
      }
    } else {
      arg1 = Number(arg1.toString().replace(".", ""));
      arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
  }

})