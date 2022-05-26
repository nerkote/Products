package com.products.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
		GetingData runnableApiOne = new GetingData("https://simple-scala-api.herokuapp.com/api1");
		runnableApiOne.start();
		runnableApiOne.join();
		GetingData runnableApiTwo = new GetingData("https://simple-scala-api.herokuapp.com/api2");
		runnableApiTwo.start();
		runnableApiTwo.join();
		GetingData runnableApiThree = new GetingData("https://simple-scala-api.herokuapp.com/api3");
		runnableApiThree.start();
		runnableApiThree.join();
		GetingData runnableApiFour = new GetingData("https://simple-scala-api.herokuapp.com/api4");
		runnableApiFour.start();
		runnableApiFour.join();
		SortingData sortingData = new SortingData(runnableApiOne.getMultiValueMap(), runnableApiTwo.getMultiValueMap(), runnableApiThree.getMultiValueMap(), runnableApiFour.getMultiValueMap());
		sortingData.start();
		sortingData.join();
	}

}
