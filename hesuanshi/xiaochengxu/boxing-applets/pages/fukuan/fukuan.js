const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    customList: {},
    contactName: '',
    contactPhone: '',
    contactAddress: '',
    remark: '',
    count: 0.00,
    yunfei: 0.00,
    advancePaymentPriceTotal: 0.00,
    heji: 0.00,
    fareType: 0,
    fareTypes: ['寄方付', '买方付']
  },
  onLoad: function(options) {
    that = this;
    var list = app.globalData.customList;
    var count = 0.00;
    var yunfei = 0.00;
    var advancePaymentPriceTotal = 0.00;
    for (var i = 0; i < list.length; i++) {
      count = that.accAdd(count, list[i].price)
      yunfei = that.accAdd(yunfei, list[i].fare)
      console.log(" list[i].advancePaymentPrice=" + list[i].advancePaymentPrice);
      advancePaymentPriceTotal = that.accAdd(advancePaymentPriceTotal, list[i].advancePaymentPrice)
    }
    that.setData({
      customList: app.globalData.customList,
      count: count,
      yunfei: yunfei,
      advancePaymentPriceTotal: advancePaymentPriceTotal,
      heji: that.accAdd(count, yunfei)
    })
    that.getUserMorenAddress();
  },
  onShow: function() {
    if (app.globalData.address != undefined) {
      this.setData({
        contactName: app.globalData.address.name,
        contactPhone: app.globalData.address.phone,
        contactAddress: app.globalData.address.province + " " + app.globalData.address.city + " " + app.globalData.address.county + " " + app.globalData.address.address
      })
    }
  },
  //根据订单id查询收货信息
  getUserMorenAddress: function() {
    network.get('userAddress/findMorenAddress', function(res) {
      if (res.data.code == 200) {
        //如果有用户设置的默认地址 直接给默认地址
        if (res.data.data != '') {
          that.setData({
            contactName: res.data.data.name,
            contactPhone: res.data.data.phone,
            contactAddress: res.data.data.province + " " + res.data.data.city + " " + res.data.data.county + " " + res.data.data.address
          })
        }
      }
    })
  },
  bindPickerChange: function(e) {
    var index = e.detail.value;
    if (index == 0) {
      var list = that.data.customList;;
      var count = 0.00;
      var yunfei = 0.00;
      for (var i = 0; i < list.length; i++) {
        count = that.accAdd(count, list[i].price)
        yunfei = that.accAdd(yunfei, list[i].fare)
      }
      that.setData({
        count: count,
        yunfei: yunfei,
        heji: that.accAdd(count, yunfei),
        fareType: index
      })
    } else {
      var list = that.data.customList;
      var count = 0.00;
      for (var i = 0; i < list.length; i++) {
        count = that.accAdd(count, list[i].price)
      }
      that.setData({
        heji: count,
        fareType: index
      })
    }


  },
  //下拉刷新
  onPullDownRefresh: function() {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  //修改收货地址
  updateAddress: function() {
    //跳转到收货地址--带类型为订单页面
    wx.navigateTo({
      url: '/pages/addressList/addressList?type=' + "xuanzhong"
    });
  },
  //买家留言
  remarkChange: function(e) {
    this.setData({
      remark: e.detail.value
    })
  },
  //提交订单
  subOrderTap: function(e) {
    if (that.data.contactName == '' || that.data.contactPhone == '' || that.data.contactAddress == '') {
      wx.showToast({
        icon: 'none',
        title: '为确保货物顺利送达,请完善您的收货信息!',
        duration: 1500
      })
      return;
    } else {
      if (that.data.customList.length <= 0) {
        return;
      } else {
        var params = {
          customList: JSON.stringify(that.data.customList),
          fareType: that.data.fareType,
          contactName: that.data.contactName,
          contactPhone: that.data.contactPhone,
          contactAddress: that.data.contactAddress,
          remark: that.data.remark,
          formId: e.detail.formId,
          paymentType: 2 ////1:全款; 2:定金; 3:尾款
        }
        network.post('order/submitOrder', params, function(res) {
          if (res.data.code == 200) {
            wx.requestPayment({
              timeStamp: res.data.data.timestamp,
              nonceStr: res.data.data.nonce_str,
              package: res.data.data.extended,
              signType: "MD5",
              paySign: res.data.data.sign,
              success: function(e) {
                var notifyParams = {
                  outTradeNo: res.data.data.outTradeNo
                };
                network.post("pay/orderNotify", notifyParams, function(res) {
                  if (res.data.code == 200) {
                    wx.showToast({
                      title: "支付成功"
                    });
                    wx.redirectTo({
                      url: '/pages/orderDetail/orderDetail?outTradeNo=' + notifyParams.outTradeNo
                    })
                  } else {
                    wx.showToast({
                      title: "支付失败"
                    });
                    wx.redirectTo({
                      url: '/pages/orderDetail/orderDetail?outTradeNo=' + notifyParams.outTradeNo
                    })
                  }
                });
              },
              fail: function(e) {
                wx.showToast({
                  title: "支付取消"
                });
                wx.redirectTo({
                  url: '/pages/orderDetail/orderDetail?outTradeNo=' + res.data.data.outTradeNo
                })
              }
            });
          }
        })
      }
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
  onShareAppMessage: function() {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})