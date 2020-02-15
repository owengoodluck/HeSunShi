const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    calculated: {},
    printMethodArray: [],
    printMethodData: [],
    homeImagesCard: [],
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
    classifyList5:[],
    classifyList5_on:-1,
    source: 1,
    detail: {},
    printMethodName:'',
    printMethodId:0,
  },
  onLoad: function (options) {
    that = this;
    this.queryClassify5();
    this.findAdvancePayment();
  },

  queryClassify5: function () {
    var _this = this;

    network.get("boxType/queryClassify5", function (res) {
      if (res.data.code === 200) {
        that.setData({
          classifyList5: res.data.data
        })
        
        that.setData({
          homeImagesCard: [res.data.data[0].image]
        })

        that.setData({
          detail: res.data.data[0]
        }) 
        app.globalData.calculated.classifyList5 = res.data.data;
        app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([res.data.data[0].image]);
      }
      
    })
    
  },
  onShow: function () {
    //this.queryClassify5();
    this.setData({
      calculated: app.globalData.calculated
    })
    this.setData({
      homeImagesCard: app.globalData.calculated.homeImagesCard
    })
    that.setData({
      classifyList5_on: app.globalData.calculated.classifyList5_on
    })
    that.setData({
      printMethodName: app.globalData.calculated.printMethodName,
      printMethodId: app.globalData.calculated.printMethodId
    })   
    console.log("homeImagesCard==>" + app.globalData.calculated.homeImagesCard);
    console.log("classifyList5_on==>" + app.globalData.calculated.classifyList5_on);
    
  },
  findAdvancePayment:function(){
    var param = {
      key: 'advance_payment'
    }
    //console.log();
    network.post("sysConfig/findByKey", param, function (res) {
      if (res.data.code === 200) {
        
        app.globalData.calculated.advancePaymentPrice = res.data.data.itemValue;
        console.log("advancePaymentPrice==>" + res.data.data.itemValue);
      }

    })
  },
  certainTap: function (e) {
    app.globalData.calculated.typeId = e.currentTarget.dataset.id;
    app.globalData.calculated.typeName = e.currentTarget.dataset.name;
    
    for (var i = 0; i < this.data.classifyList5.length; i++){
      if (e.currentTarget.dataset.id == this.data.classifyList5[i].id){       
        this.setData({
          classifyList5_on : i,
        })
        app.globalData.calculated.classifyList5_on = i;
        /*
        this.setData({
          homeImagesCard: []
        })
        */
        app.globalData.calculated.homeImagesCard = [this.data.classifyList5[i].image];
        
        
        app.globalData.calculated.typeImage = this.data.classifyList5[i].image;     
      }
    }
    

    app.globalData.calculated.materialId = 0;
    app.globalData.calculated.materialName = "选择材质";
    

    app.globalData.calculated.printMethodId = -1;
    app.globalData.calculated.printMethodName = "选择印刷方式";

    this.data.calculated = app.globalData.calculated;
    this.setData({
      calculated: {
        materialName:app.globalData.calculated.materialName, 
        printMethodName:app.globalData.calculated.printMethodName
      }
    })
    this.setData({
      homeImagesCard: app.globalData.calculated.homeImagesCard
    }) 
    
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
  boxTypeTap: function () {
    wx.navigateTo({
      url: '/pages/typeClassify/typeClassify?source=1'
    })
  },
  boxMaterailTap: function () {
    if (this.data.calculated.typeName === '选择盒型') {
      wx.showToast({
        icon: 'none',
        title: '请先选择包装盒型'
      })
    } else {
      wx.navigateTo({
        url: '/pages/materialClassify/materialClassify?source=1&boxTypeDetailId=' + app.globalData.calculated.typeId
      })
    }
  },
  boxLayoutTap: function () {
    if (this.data.calculated.typeName === '选择盒型') {
      wx.showToast({
        icon: 'none',
        title: '请先选择包装盒型'
      })
    } else {
      wx.navigateTo({
        url: '/pages/layoutClassify/layoutClassify?source=1&boxTypeDetailId=' + app.globalData.calculated.typeId
      })
    }
  },
  boxPrintMethodTab:function(){
    if (this.data.calculated.typeName === '选择盒型') {
      wx.showToast({
        icon: 'none',
        title: '请先选择包装盒型'
      })
    } else {
      wx.navigateTo({
        url: '/pages/printMethod/printMethod?source=1&boxTypeDetailId=' + app.globalData.calculated.typeId
      })
    }
  },
  bindLengthInput: function (e) {
    app.globalData.calculated.length = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
  },
  bindWidthInput: function (e) {
    app.globalData.calculated.width = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
  },
  bindHeightInput: function (e) {
    app.globalData.calculated.height = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
  },
  bindPieceInput: function (e) {
    app.globalData.calculated.piece = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
  },
  bindPickerChange: function (e) {
    app.globalData.calculated.printMethodId = this.data.printMethodData[e.detail.value].id;
    app.globalData.calculated.printMethodName = this.data.printMethodData[e.detail.value].name;
    this.setData({
      calculated: app.globalData.calculated
    })
  },
  calculatedTap: function (e) {
    if (this.data.calculated.typeName === '选择盒型') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装盒型'
      })
    } else if (this.data.calculated.materialName === '选择材质') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装材质'
      })
    } else if (this.data.calculated.printMethodName === '选择印刷方式') {
      wx.showToast({
        icon: 'none',
        title: '请选择包装印刷方式'
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
    } else if (!wx.getStorageSync("phone")) {
      wx.navigateTo({
        url: '/pages/bindPhone/bindPhone'
      })
    } else {
      app.globalData.calculated.formId = e.detail.formId;
      this.calculated();
    }
  },
  calculated: function () {
    if (app.globalData.calculated.typeId != 0 && app.globalData.calculated.materialId != 0) {
      //var params = app.globalData.calculated;
      var params = {
        id: app.globalData.calculated.id,
        type: app.globalData.calculated.type,
        name: app.globalData.calculated.name,
        typeId: app.globalData.calculated.typeId,
        typeName: app.globalData.calculated.typeName,
        typeImage: app.globalData.calculated.typeImage,
        materialId: app.globalData.calculated.materialId,
        materialName: app.globalData.calculated.materialName,
        materialImage: app.globalData.calculated.materialImage,
        layoutId: app.globalData.calculated.layoutId,
        layoutName: app.globalData.calculated.layoutName,
        layoutImage: app.globalData.calculated.layoutImage,
        printMethodId: app.globalData.calculated.printMethodId,
        printMethodName: app.globalData.calculated.printMethodName,
        length: app.globalData.calculated.length,
        width: app.globalData.calculated.width,
        height: app.globalData.calculated.height,
        piece: app.globalData.calculated.piece,
        price: app.globalData.calculated.price,
        fare: app.globalData.calculated.fare
      }
      network.post("boxType/calculated", params, function (res) {
        if (res.data.code == 200) {
          app.globalData.calculated.price = res.data.data.price;
          app.globalData.calculated.fare = res.data.data.fare;
          console.log("res.data.data.advancePaymentPrice==>"+res.data.data.advancePaymentPrice);
          app.globalData.calculated.advancePaymentPrice = res.data.data.advancePaymentPrice;
          that.setData({
            calculated: app.globalData.calculated
          })
          wx.navigateTo({
            url: '/pages/programDetail/programDetail?isShow=1'
          })
        } else {
          wx.showToast({
            icon: 'none',
            title: res.data.message
          })
        }
      })
    } else {
      wx.navigateTo({
        url: '/pages/programDetail/programDetail?isShow=0'
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