const app = getApp();
var that;
var network = require('../../utils/network.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    printMethodName: app.globalData.calculated.printMethodName,
    printMethodId: app.globalData.calculated.printMethodId
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      printMethodId: app.globalData.calculated.printMethodId,
      printMethodName: app.globalData.calculated.printMethodName
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
   
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  selectMethod: function(e){
    console.log("id==>"+e.currentTarget.dataset.id);
    if(e.currentTarget.dataset.id == 1){
      //app.globalData.calculated.printMethodId = 1;
      //app.globalData.calculated.printMethodName = '彩色印刷';
      this.data.printMethodId = e.currentTarget.dataset.id;
      this.data.printMethodName = '彩色印刷';
      this.setData({
        printMethodName: '彩色印刷'
      })
    }
    if (e.currentTarget.dataset.id == 2) {
      //app.globalData.calculated.printMethodId = 2;
      //app.globalData.calculated.printMethodName = '单色印刷';
      this.data.printMethodId = e.currentTarget.dataset.id;
      this.data.printMethodName = '单色印刷';
      this.setData({
        printMethodName: '单色印刷'
      })
    }
    if (e.currentTarget.dataset.id == 3) {
      //app.globalData.calculated.printMethodId = 3;
      //app.globalData.calculated.printMethodName = '空白不印';
      this.data.printMethodId = e.currentTarget.dataset.id;
      this.data.printMethodName = '空白不印';
      this.setData({
        printMethodName: '空白不印'
      })
    }
  },

  commitMethod:function(){
    if (this.data.printMethodId > 3 || this.data.printMethodId < 0){
      wx.showToast({
        icon: 'none',
        title: '请先选择印刷方式'
      })
    }else{
      app.globalData.calculated.printMethodId = this.data.printMethodId;
      app.globalData.calculated.printMethodName = this.data.printMethodName;
      
      wx.switchTab({
        url: '/pages/home/home'
      })
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})