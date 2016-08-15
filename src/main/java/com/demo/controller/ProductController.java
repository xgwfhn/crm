package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		System.out.println("index14444");
		return "index"; 
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody	//加上@ResponseBody,说明改方法不会跳转到页面,而是返回json字符串
	public String add(HttpServletRequest request, Model model) {
		System.out.println(request.getParameter("name"));//获取表单数据
		return "{\"success\":\"true\"}"; 
	}
	
	@RequestMapping(value = "/add1", method = RequestMethod.POST)
	@ResponseBody	//加上@ResponseBody,说明改方法不会跳转到页面,而是返回json字符串
	public Map<String,Object> add1(HttpServletRequest request, Model model) {
		System.out.println(request.getParameter("name"));//获取表单数据
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success", true);
		return map; 
	}
} 
