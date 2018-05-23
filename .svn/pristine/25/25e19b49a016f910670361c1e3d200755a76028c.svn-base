package com.activiti.test.action.support;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.activiti.test.entity.BsUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * </p>
 * 
 */
public class BaseController implements HandlerInterceptor{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected HttpSession session;

	@Autowired
	protected ServletContext application;
	


	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 */
	protected <T> Page<T> getPage() {
		return getPage(10);
	}

	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 * 
	 * @param size
	 *            每页显示数量
	 * @return
	 */
	protected <T> Page<T> getPage(int size) {
		int _size = size, _index = 1;
		if (request.getParameter("_size") != null) {
			_size = Integer.parseInt(request.getParameter("_size"));
		}
		if (request.getParameter("_index") != null) {
			_index = Integer.parseInt(request.getParameter("_index"));
		}
		return new Page<T>(_index, _size);
	}

	/**
	 * 重定向至地址 url
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	protected String redirectTo(String url) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 * @return
	 */
	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object 转换对象
	 * @param format 序列化特点
	 * @return
	 */
	protected String toJson(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}
	
	protected Long getCurrentUserId(){
		BsUser user = (BsUser)session.getAttribute("user");
		return user.getId();
	}
	
	protected String getCurrentUserName(){
		BsUser user = (BsUser)session.getAttribute("user");
		return user.getName();
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("jsessionid----------"+request.getRequestedSessionId());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}

