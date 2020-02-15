package com.xseagull.boxing.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.config.TokenConfig;
import com.xseagull.boxing.util.EncryptUtils;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
	private static final String[] NOT_FILTER = new String[] { "/auth", "/upload", "/pay", "/merchant","/swagger-resources",
			"/swagger-resources/configuration/ui",
			"/swagger-resources/configuration/security",
			"/v2/api-docs",
			"/swagger-ui.html",
			"/webjars/springfox-swagger-ui/springfox.css",
			"/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
			"/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
			"/webjars/springfox-swagger-ui/springfox.js",
			"/webjars/springfox-swagger-ui/swagger-ui.css",
			"/webjars/springfox-swagger-ui/favicon-32x32.png",
			"/webjars/springfox-swagger-ui/favicon-16x16.png" };

	private static final String[] NOT_SIGN = new String[] { "/pay/orderWxPayNotify", "/upload/video", "/auth/share", "/auth/getServiceNumberSignature", "/swagger-resources",
			"/swagger-resources/configuration/ui",
			"/swagger-resources/configuration/security",
			"/v2/api-docs",
			"/swagger-ui.html",
			"/webjars/springfox-swagger-ui/springfox.css",
			"/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
			"/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
			"/webjars/springfox-swagger-ui/springfox.js",
			"/webjars/springfox-swagger-ui/swagger-ui.css",
			"/webjars/springfox-swagger-ui/favicon-32x32.png",
			"/webjars/springfox-swagger-ui/favicon-16x16.png" };

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private TokenConfig tokenConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		boolean doSign = true;
		for (String notSign : NOT_SIGN) {
			if (uri.indexOf(notSign) != -1) {
				doSign = false;
				break;
			}
		}
		
		if(request.getServerName().equals("localhost")) {
			doSign = false;
		}
		logger.info("doSign==>"+doSign);
		
		if (doSign) {
			String sign = request.getHeader("Sign");
			logger.info("sign==>"+sign);
			String timestamp = request.getHeader("timestamp");
			logger.info("timestamp==>"+timestamp);
			if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp)) {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(mapper.writeValueAsString(ResultData.warn(ResultCode.SIGN_ERROR)));
				return;
			}
			long second = (System.currentTimeMillis() - Long.valueOf(timestamp)) / 1000;
			logger.info("second==>"+second);
			
			if (second > 1800 || second < -1800) {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(mapper.writeValueAsString(ResultData.warn(ResultCode.SIGN_ERROR)));
				return;
			}
			Enumeration<String> parametes = request.getParameterNames();
			StringBuffer sb = new StringBuffer();
			while (parametes.hasMoreElements()) {
				String name = (String) parametes.nextElement();
				String value = request.getParameter(name);
				if (!StringUtils.isEmpty(name)) {
					sb.append(name).append("=").append(value).append("&");
				}
			}
			String queryStr = sb.toString();
			if (StringUtils.isEmpty(queryStr)) {
				queryStr = "timestamp=" + timestamp;
			} else {
				queryStr = queryStr + "&timestamp=" + timestamp;
			}
			String[] keys = queryStr.split("&");
			Arrays.sort(keys);
			StringBuffer reqStr = new StringBuffer();
			for (int i = 0; i < keys.length; i++) {
				if (!StringUtils.isEmpty(keys[i])) {
					reqStr.append(keys[i] + "&");
				}
			}
			reqStr.append("key=yVwlsbIrY3q22EnoYYM4nR5zqTmqed05");
			logger.info("reqStr==>"+reqStr);
			String signAfter = EncryptUtils.md5Encode(reqStr.toString()).toUpperCase();
			logger.info("signAfter==>"+signAfter);
			if (!sign.equals(signAfter)) {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(mapper.writeValueAsString(ResultData.warn(ResultCode.SIGN_ERROR)));
				return;
			}
		}

		boolean doFilter = true;
		for (String notFilter : NOT_FILTER) {
			if (uri.indexOf(notFilter) != -1) {
				doFilter = false;
				break;
			}
		}

		if (doFilter) {
			//System.out.println(request.getRequestURL());
			if(request.getServerName().equals("localhost")) {
				//final String authToken = authHeader.substring(tokenConfig.getTokenHead().length());
				//String userId = jwtTokenUtil.getUserIdFromToken(authToken);
				//String userId = jwtTokenUtil.getUserIdFromToken(authToken);
				Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
				m.put("userId", new String[] { "383" });// hardcode for testing only
				request = new ParameterRequestWrapper(request, m);
				chain.doFilter(request, response);
				return ;
			}
			String authHeader = request.getHeader(tokenConfig.getHeader());
			if (authHeader != null && authHeader.startsWith(tokenConfig.getTokenHead())) {
				final String authToken = authHeader.substring(tokenConfig.getTokenHead().length());
				String userId = jwtTokenUtil.getUserIdFromToken(authToken);
				if (!StringUtils.isEmpty(userId)) {
					if (!jwtTokenUtil.validateToken(authToken)) {
						ObjectMapper mapper = new ObjectMapper();
						response.setContentType("application/json;charset=utf-8");
						response.getWriter().write(mapper.writeValueAsString(ResultData.error(ResultCode.UNAUTHTOKEN)));
						return;
					} else {
						Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
						m.put("userId", new String[] { userId });
						request = new ParameterRequestWrapper(request, m);
					}
				} else {
					ObjectMapper mapper = new ObjectMapper();
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write(mapper.writeValueAsString(ResultData.error(ResultCode.UNAUTHTOKEN)));
					return;
				}
			} else {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(mapper.writeValueAsString(ResultData.error(ResultCode.UNAUTHTOKEN)));
				return;
			}
		} else {
			String authHeader = request.getHeader(tokenConfig.getHeader());
			if (authHeader != null && authHeader.startsWith(tokenConfig.getTokenHead())) {
				final String authToken = authHeader.substring(tokenConfig.getTokenHead().length());
				String userId = jwtTokenUtil.getUserIdFromToken(authToken);
				if (!StringUtils.isEmpty(userId)) {
					if (jwtTokenUtil.validateToken(authToken)) {
						Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
						m.put("userId", new String[] { userId });
						request = new ParameterRequestWrapper(request, m);
					}
				}
			}
		}

		chain.doFilter(request, response);
	}
}
