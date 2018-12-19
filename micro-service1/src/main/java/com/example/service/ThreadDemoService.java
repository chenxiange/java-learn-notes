package com.example.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThreadDemoService {
	
	private static final String USER_QUERY_ALL_URL = "http://localhost:8080/user/queryAll";
	private static final String USER_COUNT_ALL_URL = "http://localhost:8080/user/countAll";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	ExecutorService executeService = Executors.newFixedThreadPool(10);

	public String singleThread() {
		
		long beginMillis = System.currentTimeMillis();
		
		String result1 = restTemplate.getForObject(USER_QUERY_ALL_URL, String.class);
		
		String result2 = restTemplate.getForObject(USER_COUNT_ALL_URL, String.class);
		
		long endMillis = System.currentTimeMillis();
		
		return "耗时: " + (endMillis - beginMillis) + ", 结果1: " + result1 + ", 结果2: " + result2;
		
	}
	
	public String multiThread() {
		
		long beginMillis = System.currentTimeMillis();
		
		Callable<String> callable1 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return restTemplate.getForObject(USER_QUERY_ALL_URL, String.class);
			}
		};
		
		Callable<String> callable2 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return restTemplate.getForObject(USER_COUNT_ALL_URL, String.class);
			}
		};
		
		
		FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
		FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
		
//		new Thread(futureTask1).start();
//		new Thread(futureTask2).start();
		
		executeService.submit(futureTask1);
		executeService.submit(futureTask2);
		
		try {
			String result1 = futureTask1.get();
			String result2 = futureTask2.get();
			
			long endMillis = System.currentTimeMillis();
			
			return "耗时: " + (endMillis - beginMillis) + ", 结果1: " + result1 + ", 结果2: " + result2;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}
	
}
