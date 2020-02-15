const app = getApp();

var network = require('../../utils/network.js');
var that;
Page({
  data: {
    addressList: {},
    type: '',
    orderId: ''
  },
  onLoad: function(options) {
    that = this;
    if (options.orderId) {
      that.setData({
        type: options.type,
        orderId: options.orderId
      })
    } else {
      that.setData({
        type: options.type
      })
    }
  },
  onShow: function() {
    that.findAddressList()
  },
  //下拉刷新
  onPullDownRefresh: function() {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  //查询用户的收货地址
  findAddressList: function() {
    network.get('userAddress/findAddressList', function(res) {
      if (res.data.code == 200) {
        that.setData({
          addressList: res.data.data
        })
      }
    })
  },
  //设为默认
  setMoren: function(e) {
    network.get('userAddress/setMoren/' + that.data.addressList[e.currentTarget.dataset.index].id, function(res) {
      if (res.data.code == 200) {
        that.findAddressList();
      }
    })
  },
  //删除地址
  delAddress: function(e) {
    wx.showModal({
      title: '温馨提示',
      content: '是否删除该地址?',
      cancelColor: '#999999',
      success: function(res) {
        if (res.confirm) {
          network.get('userAddress/delAddress/' + that.data.addressList[e.currentTarget.dataset.index].id, function(res) {
            if (res.data.code == 200) {
              wx.showToast({
                icon: 'none',
                title: '删除成功',
                duration: 1500
              })
              that.findAddressList();
            } else {
              wx.showToast({
                icon: 'none',
                title: res.data.data.message,
                duration: 1500
              })
            }
          })
        }
      }
    })
  },
  //编辑收货地址
  editAddress: function(e) {
    app.globalData.addressDetail = that.data.addressList[e.currentTarget.dataset.index];
    wx.navigateTo({
      url: '../setAddress/setAddress?type=edit'
    })
  },
  //新增收货地址
  addAddress: function() {
    wx.navigateTo({
      url: '../setAddress/setAddress?type=add'
    })
  },
  //选中地址事件
  xuanze: function(e) {
    if (that.data.type == "xuanzhong") {
      app.globalData.address = that.data.addressList[e.currentTarget.dataset.index];
      wx.navigateBack();
    }
  },
  onShareAppMessage: function () {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})