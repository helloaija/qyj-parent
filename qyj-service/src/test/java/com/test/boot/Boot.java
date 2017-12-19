package com.test.boot;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Boot {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring-context.xml" });  
			context.start();
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
