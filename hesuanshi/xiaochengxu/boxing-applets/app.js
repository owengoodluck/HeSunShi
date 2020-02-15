App({
  onLaunch: function (options) {
    if (options.query.recommenderId) {
      wx.setStorageSync("recommenderId", options.query.recommenderId || 0);
    } else {
      wx.setStorageSync("recommenderId", 0);
    }
    if (options.query.scene) {
      var scene = decodeURIComponent(options.query.scene);
      wx.setStorageSync("recommenderId", scene.split("=")[1] || 0);
    } else {
      wx.setStorageSync("recommenderId", 0);
    }
  },
  onShow: function () {
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        if (res.system.match("iOS")) {
          that.globalData.isComeFormIOS = !0;
        }
        that.globalData.windowWidth = res.windowWidth;
        that.globalData.windowHeight = res.windowHeight;
        that.globalData.screenHeight = res.screenHeight;
        that.globalData.pixelRatio = 750 / res.windowWidth;
      }
    });
    // 获取小程序更新机制兼容
    if (wx.canIUse('getUpdateManager')) {
      const updateManager = wx.getUpdateManager()
      updateManager.onCheckForUpdate(function (res) {
        // 请求完新版本信息的回调
        if (res.hasUpdate) {
          updateManager.onUpdateReady(function () {
            wx.showModal({
              title: '更新提示',
              content: '新版本已经准备好，是否重启应用？',
              success: function (res) {
                if (res.confirm) {
                  // 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
                  updateManager.applyUpdate()
                }
              }
            })
          })
          updateManager.onUpdateFailed(function () {
            // 新的版本下载失败
            wx.showModal({
              title: '已经有新版本了哟~',
              content: '新版本已经上线啦~，请您删除当前小程序，重新搜索打开哟~',
            })
          })
        }
      })
    } else {
      // 如果希望用户在最新版本的客户端上体验您的小程序，可以这样子提示
      wx.showModal({
        title: '提示',
        content: '当前微信版本过低，无法使用该功能，请升级到最新微信版本后重试。'
      })
    }
  },
  globalData: {
    windowWidth: 0,
    windowHeight: 0,
    screenHeight: 0,
    pixelRatio: 0,
    calculated: {
      id: 0,
      type: 1,
      name: '',
      typeId: 0,
      typeName: '选择盒型',
      typeImage: '/images/home_default.png',
      materialId: 0,
      materialName: '选择材质',
      materialImage: '/images/home_default.png',
      layoutId: 0,
      layoutName: '选择版面',
      layoutImage: '/images/home_default.png',
      printMethodId: 0,
      printMethodName: '选择印刷方式',
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
      addProject:'',
      advancePaymentPrice:0,
      customNumber:''
    },
    tabIndex: 1
  }
})