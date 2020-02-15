const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    type: -1,
    pageNo: 1,
    isLastPage: false,
    dataList: [],
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      type: options.type || that.data.type,
    })
  },
  onShow: function() {
    that.setData({
      pageNo: 1,
      dataList: []
    })
    this.findOrderByStatus();
  },
  //标题点击事件
  orderTap: function(e) {
    var type = e.currentTarget.dataset.index;
    that.setData({
      type: type,
      pageNo: 1,
      dataList: []
    })
    this.findOrderByStatus();
  },
  //查询订单列表
  findOrderByStatus: function() {
    var params = {
      status: that.data.type,
      pageNo: that.data.pageNo
    }
    network.post('/order/findOrderByStatus', params, function(res) {
      if (res.data.code == 200) {
        that.setData({
          pageNo: that.data.pageNo + 1,
          isLastPage: res.data.data.isLastPage,
          dataList: that.data.dataList.concat(res.data.data.list)
        })
      }
    })
  },
  //下拉刷新数据
  onReachBottom: function() {
    if (that.data.isLastPage) {
      wx.showToast({
        title: '没有更多数据了'
      });
    } else {
      that.findOrderByStatus();
    }
  },
  //查看订单详情
  gotoDetail: function(e) {
    wx.navigateTo({
      url: '/pages/orderDetail/orderDetail?outTradeNo=' + that.data.dataList[e.currentTarget.dataset.index].businessNumber
    })
  },
  copyText: function (e) {
    wx.setClipboardData({
      data: e.currentTarget.dataset.text,
      success: function (res) {
        wx.getClipboardData({
          success: function (res) {
            wx.showToast({
              title: '复制成功'
            })
          }
        })
      }
    })
  },
  //取消订单
  quxiaoTap: function(e) {
    var orderId = that.data.dataList[e.currentTarget.dataset.index].id;
    wx.showModal({
      title: '温馨提示',
      content: '是否取消该订单?',
      cancelColor: '#999999',
      success: function(res) {
        if (res.confirm) {
          network.get('order/quxiaoOrder/' + orderId, function(res) {
            if (res.data.code == 200) {
              wx.showToast({
                icon: 'none',
                title: '您已取消',
                duration: 1500
              })
              that.setData({
                pageNo: 1,
                dataList: []
              })
              that.findOrderByStatus();
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
  //去付款
  fukuanTap: function(e) {
    var list = that.data.dataList[e.currentTarget.dataset.index];
    var params = {
      orderId: list.id,
      countPrice: list.countPrice,
      businessNumber: list.businessNumber,
      orderNumber: list.orderNumber
    }
    network.post('order/secondPay', params, function(res) {
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

  },
  //确认收货
  querenTap: function(e) {
    var orderId = that.data.dataList[e.currentTarget.dataset.index].id;
    wx.showModal({
      title: '温馨提示',
      content: '是否确定收到货品?',
      cancelColor: '#999999',
      success: function(res) {
        if (res.confirm) {
          network.get('order/confirmReceipt/' + orderId, function(res) {
            if (res.data.code == 200) {
              wx.showToast({
                icon: 'none',
                title: '您已确认收货!',
                duration: 1500
              })
              that.setData({
                pageNo: 1,
                dataList: []
              })
              that.findOrderByStatus();
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
  //查看物流
  lookWuliuTap: function(e) {
    // wx.navigateTo({
    //   url: '/pages/expressDetail/expressDetail?orderId=' + that.data.dataList[e.currentTarget.dataset.index].id + '&cover=' + that.data.dataList[e.currentTarget.dataset.index].orderDetailVO[0].layoutImage,
    // })
    network.get('express/findExpressDetail/' + that.data.dataList[e.currentTarget.dataset.index].id, function (res) {
      if (res.data.code == 200) {
        wx.navigateToMiniProgram({
          appId: 'wx6885acbedba59c14',
          path: 'pages/result/result?nu=' + res.data.data.serial + '&com=' + res.data.data.type 
        })
      }
    })
    
  },
  //下拉刷新
  onPullDownRefresh: function() {
    //停止下拉刷新
    wx.stopPullDownRefresh()
  },
  onShareAppMessage: function () {
    return {
      title: "盒算师: 包装智能报价,包装定制管理",
      path: '/pages/home/home?recommenderId=' + wx.getStorageSync("userId"),
      imageUrl: '/images/share.png'
    }
  }
})