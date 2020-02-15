const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    windowWidth: 0,
    commissionData: {}
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function() {
    that.findCommissionData();
  },
  findCommissionData: function() {
    network.get('promoter/findCommissionData', function(res) {
      if (res.data.code == 200) {
        that.setData({
          commissionData: res.data.data
        })
      }
    })
  },
  tixianTap: function() {
    app.globalData.canCommission = that.data.commissionData.canCommission;
    app.globalData.pendingCommission = that.data.commissionData.pendingCommission
    wx.navigateTo({
      url: '/pages/goWithdraw/goWithdraw'
    })
  },
  inviteTap: function(){
    wx.navigateTo({
      url: '/pages/myUser/myUser'
    })
  },
  goCommissionTap: function () {
    wx.navigateTo({
      url: '/pages/incomeAndExpenses/incomeAndExpenses'
    })
  },
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  shareTap: function () {
    network.get('/promoter/addNumber', function (res) { })
  },
  createCodeTap: function () {
    network.get('/promoter/addNumber', function (res) { })
    wx.navigateTo({
      url: '/pages/preview/preview'
    });
  },
  homeTap: function () {
    app.globalData.calculated = {
      id: 0,
      type: 1,
      name: '',
      typeId: 0,
      typeName: '请选择包装盒型',
      typeImage: '/images/home_default.png',
      materialId: 0,
      materialName: '请选择包装材质',
      materialImage: '/images/home_default.png',
      layoutId: 0,
      layoutName: '请选择包装版面',
      layoutImage: '/images/home_default.png',
      printMethodId: 0,
      printMethodName: '请选择印刷方式',
      length: '',
      width: '',
      height: '',
      piece: '',
      price: '0',
      fare: '0'
    }
    wx.switchTab({
      url: '/pages/home/home'
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