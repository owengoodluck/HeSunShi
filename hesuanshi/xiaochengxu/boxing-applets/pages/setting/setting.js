var network = require('../../utils/network.js');
var that;
Page({
  data: {
    windowWidth: 0,
    userDetail: {},
    gender: 0,
    region: [],
    phone: '',
    nickName: '',
    province: '',
    city: '',
    status: -1
  },
  onLoad: function(options) {
    that = this;
  },
  onShow: function() {
    that.getUserDetail();
  },
  //查询用户信息
  getUserDetail: function() {
    network.get('user/getUserDetail', function(res) {
      if (res.data.code == 200) {
        that.setData({
          userDetail: res.data.data,
          nickName: res.data.data.nickName,
          gender: res.data.data.gender,
          province: res.data.data.province,
          city: res.data.data.city,
          status: res.data.data.status,
          phone: res.data.data.phone || '请绑定手机号'
        })
        if (res.data.data.province != '') {
          if (res.data.data.city != '') {
            var arr = [res.data.data.province, res.data.data.city]
            that.setData({
              region: arr
            })
          }
        }
      }
    })
  },
  //昵称发生变化
  nameChange: function(e) {
    that.setData({
      nickName: e.detail.value
    })
  },
  //性别单选框
  radioChange: function(e) {
    that.setData({
      gender: e.detail.value
    })
  },
  //选中地区
  bindRegionChange: function(e) {
    that.setData({
      region: e.detail.value,
      province: e.detail.value[0],
      city: e.detail.value[1]
    })
  },
  //绑定手机号码
  phoneTap: function() {
    wx.navigateTo({
      url: '/pages/bindPhone/bindPhone'
    })
  },
  //点击实名认证
  certificationTap: function() {
    wx.navigateTo({
      url: '/pages/certification/certification'
    })
  },
  //提交保存
  submit: function() {
    var params = {
      nickName: that.data.nickName,
      phone: that.data.phone,
      gender: that.data.gender,
      province: that.data.province,
      city: that.data.city
    }
    network.post('user/update', params, function(res) {
      if (res.data.code == 200) {
        wx.reLaunch({
          url: '/pages/my/my'
        })
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