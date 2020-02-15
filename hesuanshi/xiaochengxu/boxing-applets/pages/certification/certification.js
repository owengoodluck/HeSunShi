var network = require('../../utils/network.js');
var that;
Page({
  data: {
    userCompanyDetail: {},
    region: [],
    regionSelect: '请选择所在地区',
    id: 0,
    companyName: '',
    creditCode: '',
    province: '',
    city: '',
    address: '',
    businessLicense: '',
    addHidden: true,
    status: -1
  },
  onLoad: function(options) {
    that = this;
  },
  onShow: function() {
    that.getUserCompanyDetail();
  },
  //查询用户信息
  getUserCompanyDetail: function() {
    network.get('user/getUserCompanyDetail', function(res) {
      if (res.data.code == 200) {
        that.setData({
          userCompanyDetail: res.data.data,
          id: res.data.data.id || that.data.id,
          companyName: res.data.data.companyName || that.data.companyName,
          creditCode: res.data.data.creditCode || that.data.creditCode,
          province: res.data.data.province || that.data.province,
          city: res.data.data.city || that.data.city,
          address: res.data.data.address || that.data.address,
          businessLicense: res.data.data.businessLicense || that.data.businessLicense,
          status: res.data.data.status || -1
        })
        if (res.data.data.businessLicense) {
          that.setData({
            addHidden: false
          })
        }
        if (res.data.data.province) {
          if (res.data.data.city) {
            that.setData({
              regionSelect: res.data.data.province + "," + res.data.data.city
            })
          }
        }
      }
    })
  },
  //企业昵称发生变化
  companyNameChange: function(e) {
    that.setData({
      companyName: e.detail.value
    })
  },
  //信用代码发生变化
  creditCodeChange: function(e) {
    that.setData({
      creditCode: e.detail.value
    })
  },
  //选中地区
  bindRegionChange: function(e) {
    that.setData({
      region: e.detail.value,
      province: e.detail.value[0],
      city: e.detail.value[1],
      regionSelect: e.detail.value[0] + ',' + e.detail.value[1]
    })
  },
  //详细地址发生变化
  addressChange: function(e) {
    that.setData({
      address: e.detail.value
    })
  },
  //上传营业执照
  chooseImage: function() {
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success(res) {
        const tempFilePaths = res.tempFilePaths
        wx.uploadFile({
          url: network.serverUrl + 'upload/video',
          filePath: tempFilePaths[0],
          name: 'file',
          success: function(res) {
            that.setData({
              businessLicense: JSON.parse(res.data).data,
              addHidden: false
            })
          },
          fail: function(err) {
            console.log(err)
          }
        })
      }
    })
  },
  //删除营业执照
  del: function() {
    that.setData({
      businessLicense: "",
      addHidden: true
    })
  },
  //预览
  preview: function(e) {
    wx.previewImage({
      current: that.data.businessLicense,
      urls: [that.data.businessLicense]
    })
  },
  //提交保存
  submit: function() {
    if (!this.data.companyName) {
      wx.showToast({
        icon: 'none',
        title: '请输入企业名称'
      })
    } else if (!this.data.creditCode) {
      wx.showToast({
        icon: 'none',
        title: '请输入统一社会信用代码'
      })
    } else if (!this.data.province) {
      wx.showToast({
        icon: 'none',
        title: '请选择所在地区'
      })
    } else if (!this.data.address) {
      wx.showToast({
        icon: 'none',
        title: '请输入详细地址'
      })
    } else if (!this.data.businessLicense) {
      wx.showToast({
        icon: 'none',
        title: '请上传营业执照'
      })
    } else {
      var params = {
        id: that.data.id,
        companyName: that.data.companyName,
        creditCode: that.data.creditCode,
        province: that.data.province,
        city: that.data.city,
        address: that.data.address,
        businessLicense: that.data.businessLicense
      }
      network.post('user/saveUserCompany', params, function(res) {
        if (res.data.code == 200) {
          wx.reLaunch({
            url: '/pages/my/my'
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