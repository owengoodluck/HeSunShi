const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    avatar: "/images/avatar.png",
    nickName: "点击登录，体验更多",
    userId: '',
    login: false,
    url: '',
    orderNum: {}
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      avatar: wx.getStorageSync("avatarUrl") || that.data.avatar,
      nickName: wx.getStorageSync("nickName") || that.data.nickName,
      userId: wx.getStorageSync("userId") || that.data.userId
    })
    if (wx.getStorageSync("token") != '') {
      that.setData({
        login: true
      })
    }
  },
  onShow: function() {
    that.setData({
      avatar: wx.getStorageSync("avatarUrl") || that.data.avatar,
      nickName: wx.getStorageSync("nickName") || that.data.nickName,
      userId: wx.getStorageSync("userId") || that.data.userId
    })
    if (wx.getStorageSync("token") != '') {
      that.setData({
        login: true
      })
    }
    that.findOrderNum();
  },
  onGotUserInfo: function(e) {
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
          network.post("auth/login", params, function(res) {
            if (res.data.code === 200) {
              wx.setStorageSync("avatarUrl", e.detail.userInfo.avatarUrl);
              wx.setStorageSync("nickName", e.detail.userInfo.nickName);
              wx.setStorageSync("token", res.data.data.token_type + res.data.data.access_token);
              wx.setStorageSync("userId", res.data.data.userId);
              that.setData({
                avatar: wx.getStorageSync("avatarUrl") || that.data.avatar,
                nickName: wx.getStorageSync("nickName") || that.data.nickName,
                userId: wx.getStorageSync("userId") || that.data.userId,
                login: true
              })
            }
          })
        }
      })
    }
  },
  findOrderNum: function() {
    network.get('order/findOrderNum', function(res) {
      if (res.data.code == 200) {
        that.setData({
          orderNum: res.data.data
        })
      }
    })
  },
  //我的金额
  myMoneyTap: function() {
    wx.navigateTo({
      url: '/pages/myMoney/myMoney'
    })
  },
  //我的佣金
  commissionTap: function() {
    wx.navigateTo({
      url: '/pages/myCommission/myCommission'
    })
  },
  //我的订单里的图标点击事件
  orderTap: function(e) {
    var tap = e.currentTarget.dataset.index;
    wx.navigateTo({
      url: '/pages/orderList/orderList?type=' + tap
    })
  },
  //跳转退款列表
  tuikuanTap: function() {
    wx.navigateTo({
      url: '/pages/refundList/refundList',
    })
  },
  //设置个人资料
  setting: function() {
    if (!that.data.login) {
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
    wx.navigateTo({
      url: '/pages/setting/setting'
    });
  },
  //关于我们
  womenTap: function() {
    wx.navigateTo({
      url: '/pages/women/women'
    });
  },
  //推广会员
  promotionTap: function() {
    network.get('promoter/getPromoterByUserId', function(res) {
      if (res.data.code == 200) {
        if (res.data.data === '-1') {
          wx.navigateTo({
            url: '/pages/registerPromoter/registerPromoter'
          })
        } else if (res.data.data === '0') {
          wx.navigateTo({
            url: '/pages/promoterReview/promoterReview'
          });
        } else if (res.data.data === '1') {
          wx.navigateTo({
            url: '/pages/myCommission/myCommission'
          });
        } else if (res.data.data === '2') {
          wx.navigateTo({
            url: '/pages/promoterFail/promoterFail'
          });
        }
      }
    })

  },
  //收货地址
  addressTap: function() {
    wx.navigateTo({
      url: '/pages/addressList/addressList?type=' + "my"
    });
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})