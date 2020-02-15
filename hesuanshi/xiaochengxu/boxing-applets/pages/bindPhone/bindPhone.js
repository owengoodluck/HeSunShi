const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    windowWidth: 0,
    currentIndex: -1,
    code: '',
    phone: '',
    tips: '获取验证码',
    currentTime: 60,
    phoneCode: '',
    avatar: "/images/avatar.png",
    nickName: "登录账号，体验更多功能",
  },
  onLoad: function (options) {
    that = this;
    that.setData({
      avatar: wx.getStorageSync("avatarUrl") || that.data.avatar,
      nickName: wx.getStorageSync("nickName") || that.data.nickName
    })
  },
  onShow: function () {
    wx.login({
      success: res => {
        this.setData({
          code: res.code
        })
      }
    })
  },
  //选中那种方式
  xuanzhe: function (e) {
    this.setData({
      currentIndex: e.currentTarget.dataset.index
    });
  },
  //微信授权
  getPhoneNumber: function (e) {
    var params = {
      iv: e.detail.iv,
      encrypted: e.detail.encryptedData,
      code: that.data.code
    }
    //调用后台接口获取用户手机号码
    network.post("auth/getUserPhone", params, function (res) {
      if (res.data.code === 200) {
        var phone = JSON.parse(res.data.data.data).phoneNumber;
        wx.setStorageSync("phone", phone);
        wx.navigateBack();
      }
    })
  },
  //手机号填写事件
  phoneChange: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },
  //验证码填写
  phoneCodehange: function (e) {
    this.setData({
      phoneCode: e.detail.value
    })
  },
  //提交手机号码登录
  submit: function () {
    var guize = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
    if (!that.data.phone) {
      wx.showToast({
        icon: 'none',
        title: '请输入手机号'
      })
    } else if (!guize.test(that.data.phone)) {
      wx.showToast({
        icon: 'none',
        title: '手机号格式不正确'
      })
    } else if (!that.data.phoneCode) {
      wx.showToast({
        icon: 'none',
        title: '请输入验证码'
      })
    } else {
      var params = {
        phone: that.data.phone,
        code: that.data.phoneCode
      }
      network.post('user/updatPhone', params, function (res) {
        if (res.data.code == 200) {
          wx.setStorageSync("phone", that.data.phone);
          wx.navigateBack()
        }
      })
    }
  },
  //获取验证码
  pCodeTap: function () {
    if (that.data.tips == '获取验证码' || that.data.tips == '重新获取') {
      var guize = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
      if (!that.data.phone) {
        wx.showToast({
          icon: 'none',
          title: '请输入手机号!'
        })
      } else if (!guize.test(that.data.phone)) {
        wx.showToast({
          icon: 'none',
          title: '手机号格式不正确'
        })
      } else {
        network.post('auth/sendTextCode/' + that.data.phone, {}, function (res) {
          if (res.data.code == 200) {
            var currentTime = that.data.currentTime
            var interval = setInterval(function () {
              currentTime--;
              that.setData({
                tips: currentTime + '秒'
              })
              if (currentTime <= 0) {
                clearInterval(interval)
                that.setData({
                  tips: '重新获取',
                  currentTime: 60
                })
              }
            }, 1000)
          }
        })
      }
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