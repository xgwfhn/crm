package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		System.out.println("index14444");
		return "index"; 
	}
	
	/**跳转到多记录提交页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recordsSubmit", method = RequestMethod.GET)
	public String recordsSubmit(HttpServletRequest request, Model model) {
		System.out.println("recordsSubmit");
		return "recordsSubmit"; 
	}
	
	/**测试json字符串
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody	//加上@ResponseBody,说明改方法不会跳转到页面,而是返回json字符串
	public String add(HttpServletRequest request, Model model) {
		System.out.println(request.getParameter("name"));//获取表单数据
		return "{\"success\":\"true\"}"; 
	}
	
	/**测试将map转为json字符串
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add1", method = RequestMethod.POST)
	@ResponseBody	//加上@ResponseBody,说明改方法不会跳转到页面,此处调用spring工具类 自动将map转为 json字符串   
	public Map<String,Object> add1(HttpServletRequest request, Model model) {
		System.out.println(request.getParameter("name"));//获取表单数据
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success", true);
		return map; 
	}
	
	
	/**批量提交表格中的多条记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/batchSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> batchSubmit(HttpServletRequest request, Model model) {
		String json = request.getParameter("dataJson");     
		 JSONArray array =null;
		 //= JSONArray.from;  
		    Object[] obj = new Object[array.size()];  
		    for (int i = 0; i < array.size(); i++) {  
		        JSONObject jsonObject = array.getJSONObject(i);  
		        obj[i] = JSONObject.toBean(jsonObject, clazz);  
		    }  
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success", true);
		return map; 
	}
	
} 
