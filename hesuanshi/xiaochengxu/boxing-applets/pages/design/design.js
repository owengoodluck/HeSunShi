const app = getApp();
var that;
var network = require('../../utils/network.js');
// pages/design/design.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    source: 2,
    classifyList: [],
    customNumber: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    that = this;
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.queryClassifyDetailByTId();
    this.setData({
      customNumber: app.globalData.calculated.customNumber
    })
    //console.log("customNumber==>" + app.globalData.calculated.customNumber);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  queryClassifyDetailByTId: function(){
    network.get("boxLayout/queryClassifyDetailByTId?tId=" + app.globalData.calculated.typeId, function (res) {
      if (res.data.code === 200) {
        that.setData({
          classifyList: res.data.data
        })
      }
    })
  },
  gotoDetail: function (e) {
    wx.navigateTo({
      url: '/pages/layoutDetail/layoutDetail?id=' + e.currentTarget.dataset.id + '&source=' + this.data.source + '&type=' + this.data.type
    })
  },
  copyText: function (e) {
    wx.setClipboardData({
      data: e.currentTarget.dataset.text,
      success: function (res) {
        wx.getClipboardData({
          success: function (res) {
            wx.showToast({
              title: '复制成功'
            })
          }
        })
      }
    })
  },
})