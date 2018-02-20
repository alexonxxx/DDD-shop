package com.drpicox.dddshop;

import com.drpicox.queue.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Queue.class})
public class DddShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddShopApplication.class, args);
	}
}
