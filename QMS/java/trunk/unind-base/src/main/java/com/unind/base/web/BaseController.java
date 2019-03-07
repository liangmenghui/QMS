package com.unind.base.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.unind.base.data.Paginator;

public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	/**
	 * 获取servlet属性
	 * @return
	 */
	protected final ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

	/**
	 * 获取request
	 * @return
	 */
	protected HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

	/**
	 * 获取response
	 * @return
	 */
	protected HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

	protected String getHeader(String key) {
		return getRequest().getHeader(key);
	}

	protected PageRequest getPageRequest() {
		return this.getPageRequest(null);
	}

	protected PageRequest getPageRequest(Sort sort) {
//		HttpServletRequest request = getRequest();
//		int page = 1;
//		int rows = 10000;
//		if (null != request.getParameter("page")) {
//			page = Integer.parseInt(request.getParameter("page"));
//		}
//		if(null!=request.getParameter("rows")) {
//			rows = Integer.parseInt(request.getParameter("rows"));
//		}
		Paginator paginator = new Paginator();
//		PageRequest pageRequest = new PageRequest((paginator.getPage()-1) * paginator.getRows(), paginator.getRows(), sort);
		PageRequest pageRequest = new PageRequest(paginator.getPage()-1, paginator.getRows(), sort);
		return pageRequest;
	}

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        registerCustomEidtorsForWebDataBinder(binder);
    }

    /**
     * 日期类型转换
     * 
     */
    protected void registerCustomEidtorsForWebDataBinder(WebDataBinder binder) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
