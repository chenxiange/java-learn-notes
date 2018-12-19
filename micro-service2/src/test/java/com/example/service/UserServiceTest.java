package com.example.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.example.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired  
    private UserService userService;
	
	@Test
	public void testQueryAll() {
		List<User> users = userService.queryAll();
		System.out.println(JSON.toJSONString(users));
	}
	
	@Test
	public void testCountAll() {
		System.out.println(userService.countAll());
	}

}
