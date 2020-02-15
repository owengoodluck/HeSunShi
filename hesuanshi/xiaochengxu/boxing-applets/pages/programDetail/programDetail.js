const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({
  data: {
    isShow: 0,
    calculated: {},
    unitPrice: '',
    tapType: 0,
    type: 0,
    homeImagesCard: [],
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      type: options.type || 'add',
      isShow: options.isShow,
      windowWidth: app.globalData.windowWidth
    })
  },
  onShow: function() {
    this.setData({
      calculated: app.globalData.calculated
    })
    this.calculated();
    /**
     * 
    that.setData({
      homeImagesCard: app.globalData.calculated.homeImagesCard
    })
     */
    this.buildImagesCard();
  },
  calculated: function() {
    if (app.globalData.calculated.typeId != 0 && app.globalData.calculated.materialId != 0) {
      that.setData({
        unitPrice: (that.data.calculated.price / that.data.calculated.piece).toFixed(2)
      })
    }
  },
  buildImagesCard:function(){
    /**
     * 
     
    if (app.globalData.calculated.homeImagesCard.length <=0 ){
      if (app.globalData.calculated.typeImage){
        app.globalData.calculated.homeImagesCard.concat(app.globalData.calculated.typeImage);
        if (app.globalData.calculated.materialImage){
          app.globalData.calculated.homeImagesCard.concat(app.globalData.calculated.materialImage);
          if (app.globalData.calculated.layoutImage){
            app.globalData.calculated.homeImagesCard.concat(app.globalData.calculated.layoutImage);
          }
        }

      }
      
    }
    */
    this.setData({
      homeImagesCard: app.globalData.calculated.homeImagesCard
    })
    console.log("this.data.homeImagesCard==>" + app.globalData.calculated.homeImagesCard);
  },
  

  bindNameInput: function(e) {
    app.globalData.calculated.name = e.detail.value;
    this.setData({
      calculated: app.globalData.calculated
    })
  },

  //弹窗结束
  _cancelEvent: function(e) {
    if (that.data.tapType == 0) {
      that.save();
    } else if (that.data.tapType == 1) {
      that.ordersubmit();
    }
  },
  _cancelAddProject: function(e){
    
  },
  saveCustom: function(e) {
    that.setData({
      tapType: 0,
      type: e.currentTarget.dataset.type
    })
    if (this.data.calculated.name === '') {
      that.selectComponent("#projectName").toggleToast();
    } else {
      that.save();
    }
  },
  addProject:function(e){
    console.log("ooo===>");
    if (app.globalData.calculated.name == '') {
      wx.showToast({
        icon: 'none',
        title: '请先保存方案'
      })
      //that.selectComponent("#projectName").toggleToast();
    } else {
      that.selectComponent("#projectSelect").toggleToast();
    }

    /**
     * 
    if (this.data.calculated.addProject === '') {
      that.selectComponent("#projectSelect").toggleToast();
    } else {
      that.saveProjectSelect();
    }
     */
  },
  save: function() {
    if (app.globalData.calculated.layoutId === 0) {
      wx.showModal({
        title: '温馨提示',
        content: '请在PC端上传设计稿',
        success(response) {
          if (response.confirm) {
            app.globalData.calculated.type = that.data.type;
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
              fare: app.globalData.calculated.fare,
              advancePaymentPrice: app.globalData.calculated.advancePaymentPrice
            }
            var url = "";
            if (app.globalData.calculated.id != 0 && app.globalData.calculated.id != ""){
              url = "customSpecification/update";
            }else{
              url = "customSpecification/insert";
            }
            network.post(url, params, function(res) {
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
                  fare: '0',
                  homeImagesCard: [],
                  classifyList: [],
                  classifyList5: [],
                  classifyList5_on: -1,
                  addProject: ''
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
        }
      })
    } else {
      app.globalData.calculated.type = that.data.type;
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
        fare: app.globalData.calculated.fare,
        advancePaymentPrice: app.globalData.calculated.advancePaymentPrice
      }
      var url = "";
      if (app.globalData.calculated.id != 0 && app.globalData.calculated.id != "") {
        url = "customSpecification/update";
      } else {
        url = "customSpecification/insert";
      }
      network.post(url, params, function(res) {
        if (res.data.code == 200) {
          app.globalData.calculated = {
            id: 0,
            type: 1,
            name: '',
            typeId: 0,
            typeName: '请选择盒型',
            typeImage: '/images/home_default.png',
            materialId: 0,
            materialName: '请选择材质',
            materialImage: '/images/home_default.png',
            layoutId: 0,
            layoutName: '请选择版面',
            layoutImage: '/images/home_default.png',
            printMethodId: 0,
            printMethodName: '请选择印刷方式',
            length: '',
            width: '',
            height: '',
            piece: '',
            price: '0',
            fare: '0',
            homeImagesCard: [],
            classifyList: [],
            classifyList5: [],
            classifyList5_on: -1,
            addProject: ''
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
    that.setData({
      tapType: 1
    })
    if (this.data.calculated.name === '') {
      that.selectComponent("#projectName").toggleToast();
    } else {
      app.globalData.calculated.type = 1;
      

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
        fare: app.globalData.calculated.fare,
        advancePaymentPrice: app.globalData.calculated.advancePaymentPrice
      }
      var url = "";
      if (app.globalData.calculated.id != 0 && app.globalData.calculated.id != "") {
        url = "customSpecification/update";
      } else {
        url = "customSpecification/insert";
      }
      network.post(url, params, function(res) {
        if (res.data.code == 200) {
          app.globalData.calculated = {
            id: 0,
            type: 1,
            name: '',
            typeId: 0,
            typeName: '请选择盒型',
            typeImage: '/images/home_default.png',
            materialId: 0,
            materialName: '请选择材质',
            materialImage: '/images/home_default.png',
            layoutId: 0,
            layoutName: '请选择版面',
            layoutImage: '/images/home_default.png',
            printMethodId: 0,
            printMethodName: '请选择印刷方式',
            length: '',
            width: '',
            height: '',
            piece: '',
            price: '0',
            fare: '0',
            homeImagesCard: [],
            classifyList: [],
            classifyList5: [],
            classifyList5_on: -1,
            addProject: ''
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
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})