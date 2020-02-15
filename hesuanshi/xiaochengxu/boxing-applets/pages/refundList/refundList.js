const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    pageNo: 1,
    isLastPage: false,
    dataList: []
  },
  onLoad: function(options) {
    that = this;

  },
  onShow: function() {
    that.findRefundList();
  },
  onPullDownRefresh: function() {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  onReachBottom: function() {
    if (that.data.isLastPage) {
      wx.showToast({
        title: '没有更多数据了'
      });
    } else {
      that.findRefundList();
    }
  },
  //查询退款列表
  findRefundList: function() {
    var params = {
      pageNo: that.data.pageNo
    }
    network.post('/refund/findRefundList', params, function(res) {
      if (res.data.code == 200) {
        that.setData({
          pageNo: that.data.pageNo + 1,
          isLastPage: res.data.data.isLastPage,
          dataList: that.data.dataList.concat(res.data.data.list)
        })
      }
    })
  },
  detailTap:function(e){
    wx.navigateTo({
      url: '/pages/orderDetail/orderDetail?outTradeNo=' + e.currentTarget.dataset.businessnumber
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