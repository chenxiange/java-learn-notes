package com.example.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.example.domain.User;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testQueryAll() throws Exception {
		MvcResult result = mockMvc.perform(get("/user/queryAll"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testCountAll() throws Exception {
		MvcResult result = mockMvc.perform(get("/user/countAll"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
			System.out.println(result.getResponse().getContentAsString());
	}
	
//	@Test
//	public void testQ1() throws Exception {
//
//		Map<String, Object> map = new HashMap<>();
//    	map.put("address", "山东");
//    	
//		MvcResult result = mockMvc.perform(post("/info?address=合肥").content(JSONObject.toJSONString(map)))
//				.andExpect(status().isOk())// 
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))//text/plain;charset=UTF-8
//				.andReturn();// 返回执行请求的结果
//
//		System.out.println(result.getResponse().getContentAsString());
//	}
}
