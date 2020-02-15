const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    tabIndex: 1,
    selected: 0,
    pageNo: 1,
    isLastPage: false,
    dataList: [],
    countPrice: 0,
    quanxuan: 0,
    selList: [],
    windowWidth: 0
  },
  onLoad: function(options) {
    that = this;
  },
  onShow: function() {
    that.setData({
      windowWidth: app.globalData.windowWidth,
      tabIndex: app.globalData.tabIndex,
      pageNo: 1,
      dataList: []
    })
    that.queryCustomByPage();
  },
  //选中那种方式
  selTab: function(e) {
    app.globalData.tabIndex = e.currentTarget.dataset.index;
    that.setData({
      tabIndex: e.currentTarget.dataset.index,
      pageNo: 1,
      dataList: []
    });
    that.queryCustomByPage();
  },
  //查询方案列表
  queryCustomByPage: function() {
    var params = {
      pageNo: that.data.pageNo,
      type: that.data.tabIndex
    }
    network.post('customSpecification/queryByPage', params, function(res) {
      if (res.data.code == 200) {
        that.setData({
          pageNo: that.data.pageNo + 1,
          isLastPage: res.data.data.isLastPage,
          dataList: that.data.dataList.concat(res.data.data.list)
        })
        var list = that.data.dataList;
        for (var i = 0; i < list.length; i++) {
          list[i].selected = 0;
        }
        that.setData({
          dataList: list,
          countPrice: 0,
          quanxuan: 0,
          selList: []
        })
      }
    })
  },
  //删除方案
  delTap: function(e) {
    wx.showModal({
      title: '温馨提示',
      content: '确定要删除此方案吗?',
      cancelColor: '#999999',
      success: function(res) {
        if (res.confirm) {
          network.get('customSpecification/delete/' + e.currentTarget.dataset.id, function(res) {
            if (res.data.code == 200) {
              wx.showToast({
                icon: 'none',
                title: '删除成功!',
                duration: 1500
              })
              that.setData({
                pageNo: 1,
                dataList: []
              });
              that.queryCustomByPage();
            } else {
              wx.showToast({
                icon: 'none',
                title: res.data.data.message,
                duration: 1500
              })
            }
          })
        }
      }
    })
  },
  copyText: function(e) {
    wx.setClipboardData({
      data: e.currentTarget.dataset.text,
      success: function(res) {
        wx.getClipboardData({
          success: function(res) {
            wx.showToast({
              title: '复制成功'
            })
          }
        })
      }
    })
  },
  //选中与取消选中
  selectedTap: function(e) {
    var index = e.currentTarget.dataset.index;
    var list = that.data.dataList;
    list[index].selected = e.currentTarget.dataset.selected
    var temp = 0;
    var tempList = [];
    for (var i = 0; i < list.length; i++) {
      if (list[i].selected == 1) {
        var money = that.accAdd(list[i].fare, list[i].price)
        temp = that.accAdd(temp, money);
        tempList.push(list[i])
      }
    }
    if (tempList.length == list.length) {
      that.setData({
        dataList: list,
        countPrice: temp,
        selList: tempList,
        quanxuan: 1
      })
    } else {
      that.setData({
        dataList: list,
        countPrice: temp,
        selList: tempList,
        quanxuan: 0
      })
    }

  },
  //全选-全不选
  quanxunaTap: function(e) {
    var quanxuan = e.currentTarget.dataset.quanxuan;
    var list = that.data.dataList;
    if (quanxuan == 1) {
      var temp = 0;
      for (var i = 0; i < list.length; i++) {
        list[i].selected = 1;
        var money = that.accAdd(list[i].fare, list[i].price)
        temp = that.accAdd(temp, money);
      }
      that.setData({
        dataList: list,
        countPrice: temp,
        quanxuan: quanxuan,
        selList: list
      })
    } else {
      for (var i = 0; i < list.length; i++) {
        list[i].selected = 0;
      }
      that.setData({
        dataList: list,
        countPrice: 0,
        quanxuan: quanxuan,
        selList: []
      })
    }
  },
  //跳转到详情
  editTap: function(e) {
    //app.globalData.calculated = that.data.dataList[e.currentTarget.dataset.index];
    var data = that.data.dataList[e.currentTarget.dataset.index];
    if (data.type == "2"){
      wx.showToast({
        icon: 'none',
        title: '请耐心等待服务员报价!!'
      })
      return;
    }
    app.globalData.calculated.id = data.id;
    app.globalData.calculated.name = data.name;
    app.globalData.calculated.type = data.type;
    app.globalData.calculated.typeId = data.typeId;
    app.globalData.calculated.typeName = data.typeName;
    app.globalData.calculated.typeImage = data.typeImage;
    app.globalData.calculated.materialId = data.materialId;
    app.globalData.calculated.materialName = data.materialName;
    app.globalData.calculated.materialImage = data.materialImage;
    app.globalData.calculated.layoutId = data.layoutId;
    app.globalData.calculated.layoutName = data.layoutName;
    app.globalData.calculated.layoutImage = data.layoutImage;
    app.globalData.calculated.printMethodId = data.printMethodId;
    app.globalData.calculated.printMethodName = data.printMethodName;
    app.globalData.calculated.length = data.length;
    app.globalData.calculated.width = data.width;
    app.globalData.calculated.height = data.height;
    app.globalData.calculated.piece = data.piece;
    app.globalData.calculated.price = data.price;
    app.globalData.calculated.fare = data.fare;
    app.globalData.calculated.advancePaymentPrice = data.advancePaymentPrice;
    app.globalData.calculated.customNumber = data.customNumber;
    app.globalData.calculated.homeImagesCard = [];
    app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([data.typeImage]);
    app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([data.materialImage]);
    if (data.layoutId != 0){
      app.globalData.calculated.homeImagesCard = app.globalData.calculated.homeImagesCard.concat([data.layoutImage]);
    }
    console.log("data.typeImage==>" + data.typeImage);
    console.log("data.materialImage==>" + data.materialImage);
    console.log("data.layoutImage==>" + data.layoutImage);
    console.log("data.customNumber==>" + data.customNumber);
    console.log("homeImagesCard==>" + app.globalData.calculated.homeImagesCard);
    wx.navigateTo({
      url: '/pages/programDetail/programDetail?isShow=1'
    })
    /**
     * 
    if (that.data.tabIndex == 2) {
      wx.navigateTo({
        url: '/pages/calculated/calculated?isShow=0'
      })
    } else {
      wx.navigateTo({
        url: '/pages/calculated/calculated?isShow=1'
      })
    }
     */
  },
  //结算
  jiesuan: function() {
    //把购物车选中的商品生成一条订单
    var list = that.data.selList;
    if (list.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请选择您要结算的方案',
        duration: 1500
      })
    } else {
      app.globalData.customList = list;
      wx.navigateTo({
        url: '/pages/fukuan/fukuan'
      })
    }
  },
  onPullDownRefresh: function() {},
  onReachBottom: function() {
    if (that.data.isLastPage) {
      wx.showToast({
        title: '没有更多数据了',
      });
    } else {
      that.queryCustomByPage();
    }
  },
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  },
  //加法运算
  accAdd: function(arg1, arg2) {
    var r1, r2, m, c;
    try {
      r1 = arg1.toString().split(".")[1].length;
    } catch (e) {
      r1 = 0;
    }
    try {
      r2 = arg2.toString().split(".")[1].length;
    } catch (e) {
      r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
      var cm = Math.pow(10, c);
      if (r1 > r2) {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", "")) * cm;
      } else {
        arg1 = Number(arg1.toString().replace(".", "")) * cm;
        arg2 = Number(arg2.toString().replace(".", ""));
      }
    } else {
      arg1 = Number(arg1.toString().replace(".", ""));
      arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
  },
  homeTap: function() {
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
    wx.switchTab({
      url: '/pages/home/home'
    })
  }
})