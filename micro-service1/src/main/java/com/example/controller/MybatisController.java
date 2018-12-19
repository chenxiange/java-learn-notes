package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.User;
import com.example.mapper.UserMapper;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(Model model) throws IOException {
		
		String result = "OK";
		
		String resource = "com/example/mapper/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
//		File f = new File("D://mybatis-config.xml");
//		InputStream inputStream = new FileInputStream(f);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  User user = (User) session.selectOne("com.example.mapper.UserMapper.selectByPrimaryKey", 1);
		  result = user.getMobile();
		  
//		  UserMapper userMapper = session.getMapper(UserMapper.class);
//		  result = userMapper.selectByPrimaryKey(1).getMobile();
		} finally {
		  session.close();
		}
		
		return result;
	}
	

}