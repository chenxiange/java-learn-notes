package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	@RequestMapping(value = "/aaa", method = RequestMethod.GET)
	@ResponseBody
	public String readersBooks(Model model) {
		return userMapper.selectByPrimaryKey(1).getMobile();
	}
	
	@RequestMapping(value = "/redisSet", method = RequestMethod.GET)
	@ResponseBody
	public String redisSet(Model model) {
		redisTemplate.opsForValue().set("test:set","testValue1");
		return "OK";
	}
	
	@RequestMapping(value = "/redisGet", method = RequestMethod.GET)
	@ResponseBody
	public String redisGet(Model model) {
		return redisTemplate.opsForValue().get("test:set");
	}
	
	

}