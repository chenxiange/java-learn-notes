package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.ThreadDemoService;
/**
 * 	业务中同时要请求多个远程数据接口, 使用单线程和多线程请求耗时问题.
 * @author chenxiange
 *
 */
@Controller
@RequestMapping("/threadDemo")
public class ThreadDemoController {

	@Autowired
	private ThreadDemoService threadDemoService;
	
	// 单线程请求
	@GetMapping(value = "/singleThread")
	@ResponseBody
	public String singleThread(Model model) {
		return threadDemoService.singleThread();
	}
	
	// 多线程请求
	@RequestMapping(value = "/multiThread", method = RequestMethod.GET)
	@ResponseBody
	public String multiThread() {
		return threadDemoService.multiThread();
	}
	
}