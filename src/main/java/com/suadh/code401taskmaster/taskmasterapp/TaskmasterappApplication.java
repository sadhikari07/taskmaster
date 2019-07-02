package com.suadh.code401taskmaster.taskmasterapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskmasterappApplication {


	@Value("${amazon.aws.secretkey}")
	private static String amazonAWSSecretKey;


	public static void main(String[] args) {
		SpringApplication.run(TaskmasterappApplication.class, args);
		System.out.println(amazonAWSSecretKey);
		System.out.println();
	}

}
