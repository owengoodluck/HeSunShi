var network = require('../../utils/network.js');
var that;
Page({
  data: {
    expressDetail: {},
    orderId: 0,
    dataList: {},
    cover: ''
  },
  onLoad: function (options) {
    that = this;
    that.setData({
      orderId: options.orderId,
      cover: options.cover
    })
    //查询订单信息--快递信息--及收货地址
    network.get('express/findExpressDetail/' + options.orderId, function (res) {
      if (res.data.code == 200) {
        that.setData({
          expressDetail: res.data.data
        })
        if (res.data.data.serial != '') {
          wx.request({
            url: 'https://www.kuaidi100.com/query?type=' + res.data.data.type + '&postid=' + res.data.data.serial + '&temp=0.' + new Date().getTime(),
            success: function (res) {
              that.setData({
                dataList: res.data
              })
            }
          })
        }
      }
    })
  },
  onPullDownRefresh: function () {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  telPhone: function () {
    wx.makePhoneCall({
      phoneNumber: this.data.expressDetail.phone
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