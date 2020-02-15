var network = require('../../utils/network.js');
const app = getApp();
Page({
  data: {

  },
  onLoad: function (options) { 

  },
  onShow: function () {
    wx.login({
      success: res => {
        network.get('auth/loginByOpenId/' + res.code, function (res) {
          if (res.data.code == 200) {
            if (res.data.data != '') {
              wx.setStorageSync("avatarUrl", res.data.data.avatarUrl);
              wx.setStorageSync("nickName", res.data.data.nickName);
              wx.setStorageSync("token", res.data.data.token_type + res.data.data.access_token);
              wx.setStorageSync("userId", res.data.data.userId);
              wx.setStorageSync("phone", res.data.data.phone);
              wx.navigateBack({})
            }
          }
        })
      }
    })
  },
  onGotUserInfo: function (e) {
    if (e.detail.errMsg === "getUserInfo:ok") {
      wx.login({
        success: res => {
          var recommenderId = wx.getStorageSync("recommenderId") || 0;
          var params = {
            code: res.code,
            iv: e.detail.iv,
            encrypted: e.detail.encryptedData,
            avatar: e.detail.userInfo.avatarUrl,
            nickName: e.detail.userInfo.nickName,
            gender: e.detail.userInfo.gender,
            province: e.detail.userInfo.province,
            city: e.detail.userInfo.city,
            recommenderId: recommenderId,
          };
          network.post("auth/login", params, function (res) {
            if (res.data.code === 200) {
              wx.setStorageSync("avatarUrl", e.detail.userInfo.avatarUrl);
              wx.setStorageSync("nickName", e.detail.userInfo.nickName);
              wx.setStorageSync("token", res.data.data.token_type + res.data.data.access_token);
              wx.setStorageSync("userId", res.data.data.userId);
              wx.setStorageSync("phone", res.data.data.phone);
              wx.navigateBack({})
            }
          })
        }
      })
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