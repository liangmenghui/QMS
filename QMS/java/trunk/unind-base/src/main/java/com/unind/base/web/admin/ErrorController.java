package com.unind.base.web.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/error")
public class ErrorController extends WebController {

	@RequestMapping("404")
	public String Error404() {
		return autoView("admin/error/404");
	}

	@RequestMapping("501")
	public String Error501() {
		return autoView("admin/error/501");
	}

}
