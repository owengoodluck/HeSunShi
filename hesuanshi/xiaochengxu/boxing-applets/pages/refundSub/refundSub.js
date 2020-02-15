const app = getApp();
var network = require('../../utils/network.js');
var that;
Page({
  data: {
    reasonList: ['多拍,错拍,不想要', '价格比别的地方贵', '其他'],
    images: [],
    reason: '',
    count: 8,
    instruction: '',
    orderDetailVO: {},
    imagesNum: 0,
    money: 0
  },
  onLoad: function(options) {
    that = this;
    that.setData({
      orderDetailVO: app.globalData.orderDetailVO,
      fareType: app.globalData.fareType
    })
    if (app.globalData.fareType == 0) {
      that.setData({
        money: that.accAdd(app.globalData.orderDetailVO.price, app.globalData.orderDetailVO.fare)
      })
    } else {
      that.setData({
        money: app.globalData.orderDetailVO.price
      })
    }
  },
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  //选中退款原因
  bindRegionChange: function(e) {
    that.setData({
      reason: that.data.reasonList[e.detail.value]
    })
  },
  //选中图片
  chooseImage: function(e) {
    var selectPictureNum = e.target.dataset.num;
    that.setData({
      count: 8 - selectPictureNum,
    })
    wx.chooseImage({
      count: that.data.count, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        var tempFilePaths = res.tempFilePaths;
        that.setData({
          imagesNum: that.data.imagesNum + tempFilePaths.length,
        })
        var list = that.data.images;
        for (var i = 0; i < tempFilePaths.length; i++) {
          var filePath = tempFilePaths[i];
          var images = that.data.images;
          wx.uploadFile({
            url: network.serverUrl + 'upload/video',
            filePath: filePath,
            name: 'file',
            success: function(res) {
              images.push(JSON.parse(res.data).data)
              that.setData({
                images: images
              })
              if (that.data.images.length >= that.data.imagesNum) {
                wx.showToast({
                  icon: 'none',
                  title: '图片上传完毕!',
                  duration: 1500
                })
              }
            },
            fail: function(err) {
              console.log(err)
            }
          })
        }
      },
    })
  },
  //预览
  yulan: function(e) {
    let index = e.target.dataset.index; //预览图片的编号
    wx.previewImage({
      current: that.data.images[index], //预览图片链接
      urls: that.data.images, //图片预览list列表
      success: function(res) {},
      fail: function() {}
    })
  },
  //删除图片
  del: function(e) {
    let index = e.target.dataset.index; //预览图片的编号
    var list = that.data.images;
    list.splice(index, 1);
    that.setData({
      images: list,
      imagesNum: that.data.imagesNum - 1
    })
  },
  //输入框值变化
  instructionChange: function(e) {
    that.setData({
      instruction: e.detail.value
    })
  },
  //提交
  subRefund: function() {
    if (!that.data.reason) {
      wx.showToast({
        icon: 'none',
        title: '请选择退款原因',
        duration: 1500
      })
      return;
    } else if (that.data.imagesNum > that.data.images.length) {
      wx.showToast({
        icon: 'none',
        title: '图片正在上传,请稍后...',
        duration: 1500
      })
      return;
    } else {
      var params = ({
        orderDetailId: that.data.orderDetailVO.id,
        reason: that.data.reason,
        instruction: that.data.instruction,
        money: that.data.money,
        images: JSON.stringify(that.data.images)
      })
      network.post('refund/refundSub', params, function(res) {
        if (res.data.code == 200) {
          wx.showToast({
            title: '提交成功,等待客服审核...',
            icon: 'none',
            duration: 1500,
            mask: true,
            success: function() {
              setTimeout(function() {
                //要延时执行的代码
                wx.switchTab({
                  url: '/pages/my/my'
                });
              }, 2000) //延迟时间
            },
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
  }
})