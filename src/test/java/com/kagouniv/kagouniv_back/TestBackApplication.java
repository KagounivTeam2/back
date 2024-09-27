package com.kagouniv.kagouniv_back;

import org.springframework.boot.SpringApplication;

public class TestBackApplication {

	public static void main(String[] args) {
		SpringApplication.from(BackApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
