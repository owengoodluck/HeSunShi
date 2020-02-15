const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    id: '',
    source: 1,
    detail: {}
  },
  onLoad: function (options) {
    that = this;
    that.setData({
      id: options.id,
      source: options.source,
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function () {
    this.queryClassifyDetail();
  },
  queryClassifyDetail: function () {
    network.get("boxType/queryClassifyDetailById?id=" + this.data.id, function (res) {
      if (res.data.code === 200) {
        that.setData({
          detail: res.data.data
        })
      }
    })
  },
  certainTap: function () {
    app.globalData.calculated.typeId = this.data.detail.id;
    app.globalData.calculated.typeName = this.data.detail.name;
    app.globalData.calculated.typeImage = this.data.detail.image;
    console.log("length==>" + app.globalData.calculated.classifyList5.length);
    for (var i = 0; i < app.globalData.calculated.classifyList.length; i++) {
      if (this.data.detail.id == app.globalData.calculated.classifyList[i].id) {
        app.globalData.calculated.classifyList5_on= i;
        app.globalData.calculated.homeImagesCard = [app.globalData.calculated.classifyList[i].image]
      }
    }
    console.log("card==>" + app.globalData.calculated.homeImagesCard);
    app.globalData.calculated.materialId = 0;
    app.globalData.calculated.materialName = "请选择包装材质";
    app.globalData.calculated.materialImage = "/images/home_default.png";

    app.globalData.calculated.layoutId = 0;
    app.globalData.calculated.layoutName = "请选择包装版面";
    app.globalData.calculated.layoutImage = "/images/home_default.png";

    app.globalData.calculated.printMethodId = 0;
    app.globalData.calculated.printMethodName = '选择印刷方式';

    if (this.data.source == 1) {
      wx.switchTab({
        url: '/pages/home/home'
      })
    } else {
      wx.redirectTo({
        url: '/pages/calculated/calculated?isShow=0'
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