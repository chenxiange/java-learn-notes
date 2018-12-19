package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	public List<User> queryAll() {
		return userMapper.selectByExample(null);
	}
	
	public int countAll() {
		return userMapper.countByExample(null);
	}
	
}
