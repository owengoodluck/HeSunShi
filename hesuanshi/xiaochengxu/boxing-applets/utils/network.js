var md5 = require("./md5.min.js"); // md5加密
var serverUrl = 'https://applets.cwzjapp.com/';// 外网
// var serverUrl = 'http://9eapr9.natappfree.cc/';//内网
serverUrl = 'http://localhost:82/';// local


var key = "yVwlsbIrY3q22EnoYYM4nR5zqTmqed05";
/**
 * http 接口地址
 * method 请求方式
 * data 参数+参数值
 * func 回调函数
 */
// 请求接口
function get(url, func){
  wx.showLoading({
    title: '加载中',
    mask: true
  })
  var params = {};
  if (url.indexOf("?") != -1) {
    var paramArr = url.split("?")[1].split("&");
    for (var i = 0; i < paramArr.length; i++) {
      var res = paramArr[i].split("=");
      params[res[0]] = res[1];
    }
  }
  var header = sign(params);
  wx.request({
    url: serverUrl + url,
    header: header,
    success: function (res) {
      if (res.data.code === 200) {
        wx.hideLoading();
        func(res);
      } else if (res.data.code === 402) {
        wx.hideLoading();
        //失效
        wx.removeStorageSync("avatarUrl");
        wx.removeStorageSync("nickName");
        wx.removeStorageSync("token");
        wx.navigateTo({
          url: '../../pages/login/login'
        })
      } else {
        wx.showToast({
          title: res.data.message,
          icon: 'none',
          duration: 3000
        })
      }
    },
    fail: function (error) {
      wx.hideLoading();
      //console.log(error)
    },
    complete: function () {
      //wx.hideLoading();
    }
  })
}
function post(url, data, func){
  wx.showLoading({
    title: '加载中',
    mask: true
  })
  var header = sign(data);
  console.log("sign==>"+JSON.stringify(header));
  wx.request({
    url: serverUrl + url,
    method: "POST",
    data: data,
    header: header,
    success: function (res) {
      if (res.data.code === 200 || res.data.code === 10010) {
        wx.hideLoading();
        func(res);
      } else if (res.data.code === 402) {
        wx.hideLoading();
        //失效
        wx.removeStorageSync("avatarUrl");
        wx.removeStorageSync("nickName");
        wx.removeStorageSync("token");
        wx.navigateTo({
          url: '../../pages/login/login'
        })
      } else {
        wx.showToast({
          title: res.data.message,
          icon: 'none',
          duration: 3000
        })
      }
    },
    fail: function (error) {
      wx.hideLoading();
      //console.log(error)
    },
    complete: function () {
      //wx.hideLoading();
    }
  })
}
// 加密
function sign(data) {
  var token = wx.getStorageSync("token");
  var timestamp = new Date().getTime();
  var header = {
    'content-type': 'application/x-www-form-urlencoded',
    'Authorization': token,
    'timestamp': timestamp
  };
  var dic = data || {};
  dic.timestamp = timestamp;
  var sdic = Object.keys(dic).sort();
  var query = '';
  for (var ki in sdic) {
    query = query + sdic[ki] + '=' + dic[sdic[ki]] + '&';
  }
  query = query + 'key=' + key;
  header.Sign = md5(query).toUpperCase();
  delete dic.timestamp;
  return header;
}
module.exports = {
  serverUrl: serverUrl,
  get: get,
  post: post
}
