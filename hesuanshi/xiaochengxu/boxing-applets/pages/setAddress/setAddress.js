const app = getApp();

var network = require('../../utils/network.js');
var that;
Page({
  data: {
    region: [],
    province: '',
    city: '',
    county: '',
    status: 0,
    name: '',
    phone: '',
    address: '',
    id: 0
  },
  onLoad: function(options) {
    that = this;
    if (options.type == "add") {
      //动态设置页面标题
      wx.setNavigationBarTitle({
        title: "新建地址"
      })
    } else if (options.type == "edit") {
      //动态设置页面标题
      wx.setNavigationBarTitle({
        title: "修改地址"
      })
      var addressDetail = app.globalData.addressDetail;
      var arr = [addressDetail.province, addressDetail.city, addressDetail.county]
      that.setData({
        province: addressDetail.province,
        city: addressDetail.city,
        county: addressDetail.county,
        status: addressDetail.status,
        name: addressDetail.name,
        phone: addressDetail.phone,
        address: addressDetail.address,
        id: addressDetail.id,
        region: arr
      })
    }
  },
  onShow: function() {

  },
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  //选中地区
  bindRegionChange: function(e) {
    this.setData({
      region: e.detail.value,
      province: e.detail.value[0],
      city: e.detail.value[1],
      county: e.detail.value[2]
    })
  },
  //是否默认
  moren: function(e) {
    if (e.detail.value) {
      that.setData({
        status: 1
      })
    } else {
      that.setData({
        status: 0
      })
    }
  },
  //名字发生变化
  nameChange: function(e) {
    this.setData({
      name: e.detail.value
    })
  },
  //电话发生变化
  phoneChange: function(e) {
    this.setData({
      phone: e.detail.value
    })
  },
  //昵称发生变化
  addressChange: function(e) {
    this.setData({
      address: e.detail.value
    })
  },
  //提交保存
  saveTap: function(e) {
    var guize = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
    if (that.data.phone == undefined || that.data.phone == '' || that.data.phone == null) {
      wx.showToast({
        icon: 'none',
        title: '请输入手机号!'
      })
      return;
    } else {
      if (!guize.test(that.data.phone)) {
        wx.showToast({
          icon: 'none',
          title: '手机号格式不正确!'
        })
        return;
      }
    }
    if (that.data.name == undefined || that.data.name == '' || that.data.name == null) {
      wx.showToast({
        icon: 'none',
        title: '请输入收货人姓名!'
      })
      return;
    }
    if (that.data.address == undefined || that.data.address == '' || that.data.address == null) {
      wx.showToast({
        icon: 'none',
        title: '请输入详细门牌号!'
      })
      return;
    }
    if (that.data.region.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请选择地区!'
      })
      return;
    }

    var params = {
      id: that.data.id,
      name: that.data.name,
      phone: that.data.phone,
      status: that.data.status,
      province: that.data.province,
      city: that.data.city,
      county: that.data.county,
      address: that.data.address
    }
    network.post("userAddress/saveAddress", params, function(res) {
      if (res.data.code == 200) {
        wx.showToast({
          icon: 'none',
          title: '保存成功!'
        })
        wx.navigateBack();
      }
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