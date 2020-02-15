package com.xseagull.boxing.auth;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.config.TokenConfig;
import com.xseagull.boxing.features.dao.PosterDao;
import com.xseagull.boxing.features.entity.MerchantLogin;
import com.xseagull.boxing.features.entity.Poster;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.service.IPhoneCodeService;
import com.xseagull.boxing.pay.weixin.AppletsLoginCertificate;
import com.xseagull.boxing.pay.weixin.ServiceNumberLoginCertificate;
import com.xseagull.boxing.pay.weixin.ServiceNumberSignature;
import com.xseagull.boxing.pay.weixin.ServiceNumberUserInfo;
import com.xseagull.boxing.pay.weixin.WeiChartConfig;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.scheduler.StartupRunner;
import com.xseagull.boxing.util.EncryptUtils;
import com.xseagull.boxing.util.ValidatorUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private TokenConfig tokenConfig;
	
	@Autowired
    private IPhoneCodeService phoneCodeService;
	
	@Autowired
    private PosterDao posterDao;

	@GetMapping("/refresh")
	public ResultData refresh(HttpServletRequest request) {
		String token = request.getHeader(tokenConfig.getHeader());
		return authService.refresh(token);
	}

	/**
	 * 微信登录(根据openId)
	 */
	@GetMapping("/loginByOpenId/{code}")
	public ResultData loginByOpenId(@PathVariable String code) {
		AppletsLoginCertificate appletsLoginCertificate = WeiChartUtils.getAppletsLoginCertificate(code);
		if (appletsLoginCertificate.getErrcode() == 0) {
			return authService.loginByOpenId(appletsLoginCertificate.getOpenid());
		} else {
			return ResultData.warn(ResultCode.INVALID_CODE);
		}
	}

	/**
	 * 小程序登录
	 */
	@PostMapping("/login")
	public ResultData login(User user) {
		if (user.getNickName() != null && user.getNickName().length() > 0) {
			user.setNickName(user.getNickName().replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", ""));
		}
		if (user.getProvince() != null && user.getProvince().length() > 0) {
			user.setProvince(user.getProvince().replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", ""));
		}
		if (user.getCity() != null && user.getCity().length() > 0) {
			user.setCity(user.getCity().replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", ""));
		}
		return authService.login(user);
	}

	//解析手机号
	@PostMapping("getUserPhone")
	public ResultData getUserPhone(@RequestParam(value = "encrypted", defaultValue = "") String encrypted,
			@RequestParam(value = "iv") String iv, @RequestParam(value = "code") String code, long userId) {
		return ResultData.success(authService.getUserPhone(encrypted, iv, code, userId));
	}
	
	/**
     * 发送文本验证码
     */
    @PostMapping("sendTextCode/{phone}")
    public ResultData sendTextCode(@PathVariable String phone) {
    	if (!ValidatorUtils.isMobile(phone)) {
    		return ResultData.warn(ResultCode.PARAMETER_ERROR, "手机号格式不对");
    	} else {
    		return phoneCodeService.sendTextCode(phone);
    	}
    }
	
	@GetMapping("/share/{index}")
	public void share(@PathVariable int index, long userId, HttpServletResponse response) throws Exception {
		Poster poster = posterDao.queryDetailById(index);
		BufferedImage srcImg = getRemoteBufferedImage(poster.getImage());
		int width = srcImg.getWidth();
		int height = srcImg.getHeight(); 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost =new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + WeiChartUtils.getAppletsAccessToken().getAccess_token());

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("scene", "recommenderId=" + userId);
		params.put("page", "pages/home/home");

        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String body = JSON.toJSONString(params);  
        StringEntity stringEntity = new StringEntity(body);
        stringEntity.setContentType("image/png");
        httpPost.setEntity(stringEntity);
        
		CloseableHttpResponse res =httpClient.execute(httpPost);
		HttpEntity entity = res.getEntity();
		if (entity != null) {  
			byte[] data = EntityUtils.toByteArray(entity);
			InputStream sbs = new ByteArrayInputStream(data);
			Image qrCodeImg = ImageIO.read(sbs);
			// 加水印
	        BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = bufImg.createGraphics();
	        g.drawImage(srcImg, 0, 0, width, height, null);
	        
	        g.setClip(new RoundRectangle2D.Double((width-width/2)/2, (height-width/2)/2, width/2, width/2, 20, 20));
	        g.setComposite(AlphaComposite.SrcIn);
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g.drawImage(qrCodeImg, (width-width/2)/2, (height-width/2)/2, width/2, width/2, null);
	        
	        g.dispose(); 
	        
	        // 输出图片  
	        ByteArrayOutputStream outImgStream = new ByteArrayOutputStream();
	        ImageIO.write(bufImg, "jpg", outImgStream);
			response.setContentType("image/png");
		    OutputStream os = response.getOutputStream();
		    os.write(outImgStream.toByteArray());
		    os.flush();
		    os.close();
        }
		
		res.close();

		httpClient.close();
		
	}
	
	/**
	 * 获取远程网络图片信息
	 * @param imageURL
	 * @return
	 */
	public BufferedImage getRemoteBufferedImage(String imageURL) {
		URL url = null;
		InputStream is = null;
		BufferedImage bufferedImage = null;
		try {
			url = new URL(imageURL);
			is = url.openStream();
			bufferedImage = ImageIO.read(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(is!=null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return bufferedImage;
	}
	
	/**
	 * 服务号登录验证
	 */
	@GetMapping("/getServiceNumberLoginCertificate/{code}")
	public ResultData getServiceNumberLoginCertificate(@PathVariable String code) {
		ServiceNumberLoginCertificate serviceNumberLoginCertificate = WeiChartUtils
				.getServiceNumberLoginCertificate(code);
		if (serviceNumberLoginCertificate.getErrcode() == 0) {
			ServiceNumberUserInfo serviceNumberUserInfo = WeiChartUtils.getServiceNumberUserInfo(
					serviceNumberLoginCertificate.getAccess_token(), serviceNumberLoginCertificate.getOpenid());
			return authService.getServiceNumberLoginCertificate(serviceNumberUserInfo);
		} else {
			return ResultData.warn(ResultCode.INVALID_CODE);
		}
	}
	
	/**
     * 发送文本验证码(商户验证)
     */
	@GetMapping("sendMerchantTextCode/{phone}")
    public ResultData sendMerchantTextCode(@PathVariable String phone) {
    	if (!ValidatorUtils.isMobile(phone)) {
    		return ResultData.warn(ResultCode.PARAMETER_ERROR, "手机号格式不对");
    	} else {
    		return phoneCodeService.sendMerchantTextCode(phone);
    	}
    }
    
    /**
	 * 商户登录
	 */
	@PostMapping("/merchantLogin")
	public ResultData merchantLogin(MerchantLogin merchantLogin) {
		return authService.merchantLogin(merchantLogin);
	}
	
	/**
	 * 服务号签名验证
	 */
	@PostMapping("/getServiceNumberSignature")
	public ResultData getServiceNumberSignature(String url) {
		ServiceNumberSignature serviceNumberSignature = new ServiceNumberSignature();
		String jsapi_ticket = StartupRunner.JSAPI_TICKET.get("jsapi_ticket");
		String nonceStr = WeiChartUtils.getRandomString();
		long timestamp = System.currentTimeMillis()/1000;
		String origin = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
		serviceNumberSignature.setAppId(WeiChartConfig.SERVICE_NUMBER_APPID);
		serviceNumberSignature.setTimestamp(timestamp);
		serviceNumberSignature.setNonceStr(nonceStr);
		serviceNumberSignature.setSignature(EncryptUtils.sha1Encode(origin));
		return ResultData.success(serviceNumberSignature);
	}
}
