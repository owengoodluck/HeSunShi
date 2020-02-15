var network = require('../../utils/network.js');
Page({
  data: {
    realName: '',
    phone: '',
    content: ''
  },
  nameChange: function(e) {
    this.setData({
      realName: e.detail.value
    })
  },
  phoneChange: function(e) {
    this.setData({
      phone: e.detail.value
    })
  },
  //文本框失去焦点事件
  bindblur: function(e) {
    this.setData({
      content: e.detail.value
    })
  },
  bindinput: function(e) {
    this.setData({
      content: e.detail.value
    })
  },
  //提交保存
  saveTap: function(e) {
    var guize = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
    if (!this.data.realName) {
      wx.showToast({
        icon: 'none',
        title: '请输入真实姓名'
      })
    } else if (!this.data.phone) {
      wx.showToast({
        icon: 'none',
        title: '请输入手机号'
      })
    } else if (!guize.test(this.data.phone)) {
      wx.showToast({
        icon: 'none',
        title: '手机号格式不正确'
      })
    } else {
      var params = {
        realName: this.data.realName,
        phone: this.data.phone,
        content: this.data.content,
        formId: e.detail.formId
      }
      network.post("promoter/save", params, function(res) {
        if (res.data.code == 200) {
          wx.redirectTo({
            url: '/pages/promoterReview/promoterReview'
          });
        }
      })
    }
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})