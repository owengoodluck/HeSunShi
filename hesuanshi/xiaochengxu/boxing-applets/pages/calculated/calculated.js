const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    isShow: 0,
    calculated: {},
    printMethodArray: [],
    printMethodData: [],
    unitPrice: '',
    error: ''
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      isShow: options.isShow,
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function() {
    this.setData({
      calculated: app.globalData.calculated
    })
    this.calculated();
    this.queryClassify();
  },
  calculated: function() {
    if (app.globalData.calculated.typeId != 0 && app.globalData.calculated.materialId != 0) {
      var params = app.globalData.calculated;
      network.post("boxType/calculated", params, function(res) {
        if (res.data.code == 200) {
          app.globalData.calculated.price = res.data.data.price;
          app.globalData.calculated.fare = res.data.data.fare;
          app.globalData.calculated.advancePaymentPrice = res.data.data.advancePaymentPrice;
          that.setData({
            error: '',
            calculated: app.globalData.calculated,
            unitPrice: (res.data.data.price / that.data.calculated.piece).toFixed(2)
          })
        } else {
          that.setData({
            error: res.data.message
          })
          wx.showToast({
            icon: 'none',
            title: that.data.error
          })
        }
      })
    }
  },
  queryClassify: function() {
    network.get("printMethod/queryList", function(res) {
      if (res.data.code === 200) {
        that.setData({
          printMethodData: res.data.data
        })
        var printMethodArray = [];
        for (var i = 0; i < res.data.data.length; i++) {
          printMethodArray.push(res.data.data[i].name);
        }
        that.setData({
          printMethodArray: printMethodArray
        })
      }
    })
  },
  bindNameInput: function(e) {
    app.globalData.calculated.name = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
  },
  boxTypeTap: function() {
    wx.navigateTo({
      url: '/pages/typeClassify/typeClassify?source=2&type=' + this.data.type
    })
  },
  boxMaterailTap: function() {
    wx.navigateTo({
      url: '/pages/materialClassify/materialClassify?source=2&boxTypeDetailId=' + app.globalData.calculated.typeId + '&type=' + this.data.type
    })
  },
  boxLayoutTap: function() {
    wx.navigateTo({
      url: '/pages/layoutClassify/layoutClassify?source=2&boxTypeDetailId=' + app.globalData.calculated.typeId + '&type=' + this.data.type
    })
  },
  bindLengthInput: function(e) {
    app.globalData.calculated.length = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
    if (this.data.calculated.length != '' && this.data.calculated.width && this.data.calculated.height && this.data.calculated.piece) {
      this.calculated();
    }
  },
  bindWidthInput: function(e) {
    app.globalData.calculated.width = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
    if (this.data.calculated.length != '' && this.data.calculated.width && this.data.calculated.height && this.data.calculated.piece) {
      this.calculated();
    }
  },
  bindHeightInput: function(e) {
    app.globalData.calculated.height = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
    if (this.data.calculated.length != '' && this.data.calculated.width && this.data.calculated.height && this.data.calculated.piece) {
      this.calculated();
    }
  },
  bindPieceInput: function(e) {
    app.globalData.calculated.piece = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
    if (this.data.calculated.length != '' && this.data.calculated.width && this.data.calculated.height && this.data.calculated.piece) {
      this.calculated();
    }
  },
  bindPickerChange: function(e) {
    app.globalData.calculated.printMethodId = this.data.printMethodData[e.detail.value].id;
    app.globalData.calculated.printMethodName = this.data.printMethodData[e.detail.value].name;
    this.setData({
      calculated: app.globalData.calculated
    })
    this.calculated();
  },
  saveCustom: function(e) {
    if (this.data.calculated.name === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入方案名称'
      })
    } else if (this.data.calculated.typeName === '请选择包装盒型') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装盒型'
      })
    } else if (this.data.calculated.materialName === '请选择包装材质') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装材质'
      })
    } else if (this.data.calculated.layoutName === '请选择包装版面') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装版面'
      })
    } else if (this.data.calculated.length === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入长度'
      })
    } else if (this.data.calculated.width === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入宽度'
      })
    } else if (this.data.calculated.height === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入高度'
      })
    } else if (this.data.calculated.piece === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入数量'
      })
    } else if (this.data.calculated.printMethodName === '请选择印刷方式') {
      wx.showToast({
        icon: 'none',
        title: '请选择印刷方式'
      })
    } else if (this.data.error) {
      wx.showToast({
        icon: 'none',
        title: this.data.error
      })
    } else {
      app.globalData.calculated.type = e.currentTarget.dataset.type;
      var params = app.globalData.calculated;
      network.post("customSpecification/update", params, function(res) {
        if (res.data.code == 200) {
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
          if (that.data.isShow == 1) {
            app.globalData.tabIndex = 1;
          } else {
            app.globalData.tabIndex = 2;
          }
          wx.switchTab({
            url: '/pages/shoppingCart/shoppingCart'
          })
        }
      })
    }
  },
  ordersubmit: function(e) {
    if (this.data.calculated.name === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入方案名称'
      })
    } else if (this.data.calculated.typeName === '请选择包装盒型') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装盒型'
      })
    } else if (this.data.calculated.materialName === '请选择包装材质') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装材质'
      })
    } else if (this.data.calculated.layoutName === '请选择包装版面') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装版面'
      })
    } else if (this.data.calculated.length === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入长度'
      })
    } else if (this.data.calculated.width === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入宽度'
      })
    } else if (this.data.calculated.height === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入高度'
      })
    } else if (this.data.calculated.piece === '') {
      wx.showToast({
        icon: 'none',
        title: '请输入数量'
      })
    } else if (this.data.calculated.printMethodName === '请选择印刷方式') {
      wx.showToast({
        icon: 'none',
        title: '请选择印刷方式'
      })
    } else if (this.data.error) {
      wx.showToast({
        icon: 'none',
        title: this.data.error
      })
    } else {
      app.globalData.calculated.type = 1;
      var params = app.globalData.calculated;
      network.post("customSpecification/update", params, function(res) {
        if (res.data.code == 200) {
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
            advancePaymentPrice:'0',
            fare: '0'
          }
          var list = [];
          list.push(res.data.data)
          app.globalData.customList = list;
          wx.redirectTo({
            url: '/pages/fukuan/fukuan'
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