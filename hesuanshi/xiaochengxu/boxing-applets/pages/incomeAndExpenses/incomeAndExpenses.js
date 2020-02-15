const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    windowWidth: 0,
    pageNo: 1,
    isLastPage: false,
    dataList: []
  },
  onLoad: function (options) {
    that = this;
    that.setData({
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function () {
    that.findTixianRecord();
  },
  findTixianRecord: function () {
    var params = {
      pageNo: that.data.pageNo,
      pageSize: 20
    }
    network.post('/promoter/findCommissionRecord', params, function (res) {
      if (res.data.code == 200) {
        that.setData({
          pageNo: that.data.pageNo + 1,
          isLastPage: res.data.data.isLastPage,
          dataList: that.data.dataList.concat(res.data.data.list)
        })
      }
    })
  },
  //下拉刷新数据
  onReachBottom: function () {
    if (that.data.isLastPage) {
      wx.showToast({
        title: '没有更多数据了'
      });
    } else {
      that.findTixianRecord();
    }
  },
  //下拉刷新
  onPullDownRefresh: function () {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  onShareAppMessage: function () {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }

})