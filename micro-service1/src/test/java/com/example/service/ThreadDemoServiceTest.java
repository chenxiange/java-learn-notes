package com.example.service;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

public class ThreadDemoServiceTest {

	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String USER_QUERY_ALL_URL = "http://localhost:8080/user/queryAll";
	private static final String USER_COUNT_ALL_URL = "http://localhost:8080/user/countAll";
	
	ExecutorService tasks = Executors.newFixedThreadPool(10);
	
	@Test
	public void test1() {
		
		long beginMillis = System.currentTimeMillis();
		
		String result1 = restTemplate.getForObject(USER_QUERY_ALL_URL, String.class);
		System.out.println(result1);
		
		long millis = System.currentTimeMillis() - beginMillis;
		System.out.println("test1-任务1-耗时" + millis);
		
		String result2 = restTemplate.getForObject(USER_COUNT_ALL_URL, String.class);
		System.out.println(result2);
		System.out.println("test1-任务2-耗时" + (System.currentTimeMillis() - beginMillis - millis));
		
		System.out.println("test1-总任务耗时" + (System.currentTimeMillis() - beginMillis));
		
		
	}
	
	@Test
	public void test2() {
		
		long beginMillis = System.currentTimeMillis();
		
		Callable<String> callable1 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				String result = restTemplate.getForObject(USER_QUERY_ALL_URL, String.class);
				System.out.println("test2-任务1耗时" + (System.currentTimeMillis() - beginMillis));
				return result;
			}
		};
		
		Callable<String> callable2 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				String result = restTemplate.getForObject(USER_COUNT_ALL_URL, String.class);
				System.out.println("test2-任务2耗时" + (System.currentTimeMillis() - beginMillis));
				return result;
			}
		};
		
		FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
		FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
		
//		new Thread(futureTask1).start();
//		new Thread(futureTask2).start();
		
		tasks.submit(futureTask1);
		tasks.submit(futureTask2);
		
		try {
			System.out.println(futureTask1.get());
			System.out.println(futureTask2.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test2-总任务耗时" + (System.currentTimeMillis() - beginMillis));
		
		
	}

}
