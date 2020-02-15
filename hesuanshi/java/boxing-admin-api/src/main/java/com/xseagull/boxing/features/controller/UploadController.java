package com.xseagull.boxing.features.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.ResourceFileDao;
import com.xseagull.boxing.features.entity.ResourceFile;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private ResourceFileDao resourceFileDao;

	@PostMapping("/video/{type}")
	public ResultData uploadImg(@PathVariable String type, @RequestParam("file") MultipartFile file) {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";

		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI2nhQTtX2CkGr";
		String accessKeySecret = "BpyIgdbOCcjkViLBaWaBee5s4FiD4v";
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 设置上传的内容类型
			meta.setContentType(file.getContentType());
			String fileName = type + "/" + DigestUtils.md5Hex(file.getBytes()) + "." + suffix;
			// 上传文件流。
			ossClient.putObject("bzmall", fileName, new ByteArrayInputStream(file.getBytes()), meta);

			return ResultData.success("https://bzmall.oss-cn-shanghai.aliyuncs.com/" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		} finally {
			// 关闭OSSClient。
			ossClient.shutdown();
		}
	}

	private String readFile(InputStream inputStream) throws IOException {

		StringBuilder builder = new StringBuilder();

		try {

			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 忽略
		}

		return builder.toString().replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

	@RequestMapping(value = "/exec")
	@ResponseBody
	public String exec(HttpServletRequest request, String action) throws Exception {
		request.setCharacterEncoding("utf-8");
		if (action.equals("config")) {
			ClassPathResource resource = new ClassPathResource("config.json");
			JSONObject json = JSONObject.fromObject(readFile(resource.getInputStream()));
			return json.toString();
		} else if (action.equals("uploadimage")) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");

			// Endpoint以杭州为例，其它Region请按实际情况填写。
			String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
			// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
			// https://ram.console.aliyun.com 创建。
			String accessKeyId = "LTAI2nhQTtX2CkGr";
			String accessKeySecret = "BpyIgdbOCcjkViLBaWaBee5s4FiD4v";
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			try {
				String suffix = multipartFile.getOriginalFilename()
						.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
				// 创建上传Object的Metadata
				ObjectMetadata meta = new ObjectMetadata();
				// 设置上传的内容类型
				meta.setContentType(multipartFile.getContentType());
				String fileName = "baike-content/" + DigestUtils.md5Hex(multipartFile.getBytes()) + "." + suffix;
				// 上传文件流。
				ossClient.putObject("bzmall", fileName, new ByteArrayInputStream(multipartFile.getBytes()), meta);

				Map<String, Object> map = new HashMap<>();
				map.put("url", fileName);
				map.put("state", "SUCCESS");
				map.put("title", multipartFile.getOriginalFilename());
				map.put("original", multipartFile.getOriginalFilename());
				JSONObject json = JSONObject.fromObject(map);
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭OSSClient。
				ossClient.shutdown();
			}
			return null;
		} else {
			return null;
		}
	}

	// 处理图片上传(尺寸限制5:4)
	@PostMapping("/uploadShareImg/{type}")
	public ResultData uploadShareImg(@PathVariable String type, @RequestParam("file") MultipartFile file) {
		try {

			BufferedImage bufferedImage = ImageIO.read(file.getInputStream()); // 通过file得到InputStream，从而得到BufferedImage
			if (bufferedImage != null) {
				int width = bufferedImage.getWidth();
				int height = bufferedImage.getHeight();
				NumberFormat numberFormat = NumberFormat.getInstance();
				// 设置精确到小数点后2位
				numberFormat.setMaximumFractionDigits(2);
				String result = numberFormat.format((float) width / (float) height * 100);
				if (!result.equals("125")) {
					return ResultData.warn(ResultCode.SHAREIMG_ERROR, "图片尺寸必须为5:4");
				}
			}
			// 省略逻辑判断
		} catch (Exception e) {
			// 省略异常操作
		}
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI2nhQTtX2CkGr";
		String accessKeySecret = "BpyIgdbOCcjkViLBaWaBee5s4FiD4v";
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 设置上传的内容类型
			meta.setContentType(file.getContentType());
			String fileName = type + "/" + DigestUtils.md5Hex(file.getBytes()) + "." + suffix;
			// 上传文件流。
			ossClient.putObject("bzmall", fileName, new ByteArrayInputStream(file.getBytes()), meta);
			return ResultData.success("https://bzmall.oss-cn-shanghai.aliyuncs.com/" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		} finally {
			// 关闭OSSClient。
			ossClient.shutdown();
		}
	}

	// 处理图片上传(尺寸限制5:4)
	@PostMapping("/uploadOneByOne/{type}")
	public ResultData uploadOneByOne(@PathVariable String type, @RequestParam("file") MultipartFile file) {
		try {

			BufferedImage bufferedImage = ImageIO.read(file.getInputStream()); // 通过file得到InputStream，从而得到BufferedImage
			if (bufferedImage != null) {
				int width = bufferedImage.getWidth();
				int height = bufferedImage.getHeight();
				NumberFormat numberFormat = NumberFormat.getInstance();
				// 设置精确到小数点后2位
				numberFormat.setMaximumFractionDigits(2);
				String result = numberFormat.format((float) width / (float) height * 100);
				if (!result.equals("100")) {
					return ResultData.warn(ResultCode.SHAREIMG_ERROR, "图片尺寸必须为1:1");
				}
			}
			// 省略逻辑判断
		} catch (Exception e) {
			// 省略异常操作
		}
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI2nhQTtX2CkGr";
		String accessKeySecret = "BpyIgdbOCcjkViLBaWaBee5s4FiD4v";
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 设置上传的内容类型
			meta.setContentType(file.getContentType());
			String fileName = type + "/" + DigestUtils.md5Hex(file.getBytes()) + "." + suffix;
			// 上传文件流。
			ossClient.putObject("bzmall", fileName, new ByteArrayInputStream(file.getBytes()), meta);
			return ResultData.success("https://bzmall.oss-cn-shanghai.aliyuncs.com/" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		} finally {
			// 关闭OSSClient。
			ossClient.shutdown();
		}
	}

	@PostMapping("/resource/{type}/{number}")
	public ResultData Resource(@PathVariable String type, @PathVariable String number,
			@RequestParam("file") MultipartFile file) {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI2nhQTtX2CkGr";
		String accessKeySecret = "BpyIgdbOCcjkViLBaWaBee5s4FiD4v";
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 设置上传的内容类型
			meta.setContentType(file.getContentType());
			String fileName = "resource/" + DigestUtils.md5Hex(file.getBytes()) + "." + suffix;
			// 上传文件流。
			ossClient.putObject("bzmall", fileName, new ByteArrayInputStream(file.getBytes()), meta);
			ResourceFile resourceFile = new ResourceFile();
			resourceFile.setType(type);
			resourceFile.setName(file.getOriginalFilename());
			resourceFile.setResourceNumber(number);
			resourceFile.setFile("https://bzmall.oss-cn-shanghai.aliyuncs.com/" + fileName);
			resourceFileDao.insert(resourceFile);
			return ResultData.success("https://bzmall.oss-cn-shanghai.aliyuncs.com/" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		} finally {
			// 关闭OSSClient。
			ossClient.shutdown();
		}
	}

	public static void deleteObject(String file) {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建RAM账号。
		String accessKeyId = "LTAI2nhQTtX2CkGr";
		String accessKeySecret = "BpyIgdbOCcjkViLBaWaBee5s4FiD4v";
		String bucketName = "bzmall";
		String objectName = file.replace("https://bzmall.oss-cn-shanghai.aliyuncs.com/", "");
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 删除文件。
		ossClient.deleteObject(bucketName, objectName);
		// 关闭OSSClient。
		ossClient.shutdown();
	}
}
