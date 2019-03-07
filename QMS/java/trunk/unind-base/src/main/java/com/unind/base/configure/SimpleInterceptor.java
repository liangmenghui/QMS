package com.unind.base.configure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.unind.base.data.Paginator;

/**
 * 
 * @author tanxiang
 *
 */
public class SimpleInterceptor extends HandlerInterceptorAdapter {
	private final static Logger logger = LoggerFactory.getLogger(SimpleInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		wrapPageRequest(request);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(null!=modelAndView && StringUtils.equals(modelAndView.getViewName(), "error") && isAjaxRequest(request)) {
			response.setContentType("application/json;charset=utf-8");
		}
		super.postHandle(request, response, handler, modelAndView);
	}

    /**
     * 是Ajax请求?
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        return "x-requested-with".equals(request.getHeader("XMLHttpRequest"));
    }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if(null!=ex) {
			logger.error("调用异常", ex);
			if(isAjaxRequest(request)) {
				
			}
		}
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	private void wrapPageRequest(HttpServletRequest request) {
		Paginator paginator = new Paginator();
		int page = 1;
		int rows = 10000;
		if (null != request.getParameter("page")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(null!=request.getParameter("rows")) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		paginator.setPage(page);
		paginator.setRows(rows);
	}
}
