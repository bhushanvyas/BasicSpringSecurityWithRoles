package com.employee.demo.EmployeeDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminDashboard");
		return model;
	}

	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public ModelAndView userPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("userDashboard");
		return model;
	}

	@RequestMapping(value = { "/loginPage" }, method = RequestMethod.POST)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("loginPage");
		return model;
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;
	}

}
