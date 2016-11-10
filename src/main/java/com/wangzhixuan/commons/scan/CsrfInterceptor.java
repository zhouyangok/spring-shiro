package com.wangzhixuan.commons.scan;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wangzhixuan.commons.csrf.CsrfToken;
import com.wangzhixuan.commons.csrf.CsrfTokenBean;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.commons.utils.WebUtils;

/**
 * Csrf拦截器，用来生成或去除CsrfToken，目前基于session
 * @author L.cm
 */
@Component
public class CsrfInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LogManager.getLogger(ExceptionResolver.class);
	
	private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

	private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";

	private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = CsrfInterceptor.class
			.getName().concat(".CSRF_TOKEN");

	private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

	private String headerName = DEFAULT_CSRF_HEADER_NAME;

	private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 非控制器请求直接跳出
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		CsrfToken csrfToken = handlerMethod.getMethodAnnotation(CsrfToken.class);
		// 判断是否含有@CsrfToken注解
		if (null == csrfToken) {
			return true;
		}
		// 判断是否ajax请求
		boolean isAjax = WebUtils.isAjax(handlerMethod);
		// create、remove同时为true时异常
		if (csrfToken.create() && csrfToken.remove()) {
			logger.error("CsrfToken attr create and remove can Not at the same time to true!");
			return renderError(request, response, isAjax, "CsrfToken attr create and remove can Not at the same time to true!");
		}
		if (csrfToken.create()) {
			HttpSession session = request.getSession();
			CsrfTokenBean token = new CsrfTokenBean(headerName, parameterName, createNewToken());
			session.setAttribute(sessionAttributeName, token);
			request.setAttribute(parameterName, token);
			return true;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			return renderError(request, response, isAjax, "session is null!");
		}
		CsrfTokenBean tokenBean = (CsrfTokenBean) session.getAttribute(sessionAttributeName);
		if (tokenBean == null) {
			return renderError(request, response, isAjax, "CsrfToken is null!");
		}
		String actualToken = request.getHeader(tokenBean.getHeaderName());
		if (actualToken == null) {
			actualToken = request.getParameter(tokenBean.getParameterName());
		}
		if (!tokenBean.getToken().equals(actualToken)) {
			return renderError(request, response, isAjax, "CsrfToken not eq!");
		}
		return true;
	}
	
	private boolean renderError(HttpServletRequest request, HttpServletResponse response, 
			boolean isAjax, String message) throws IOException {
		// ajax请求直接抛出异常，因为{@link ExceptionResolver}会去处理
		if (isAjax) {
			throw new RuntimeException(message);
		}
		String queryString = request.getQueryString();
		// 被拦截前的请求URL
		String redirectUrl = request.getRequestURI();
		if (StringUtils.isNotBlank(queryString)) {
			redirectUrl = redirectUrl.concat("?").concat(queryString);
		}
		logger.info("Csrf[redirectUrl]:\t" + redirectUrl);
		response.sendRedirect(redirectUrl);
		return false;
	}
	
	private String createNewToken() {
		return UUID.randomUUID().toString();
	}

}
