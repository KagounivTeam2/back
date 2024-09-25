package com.kagouniv.kagouniv;

import com.kagouniv.kagouniv_back.BackApplication;
import org.springframework.boot.SpringApplication;

public class TestBackApplication {

	public static void main(String[] args) {
		SpringApplication.from(BackApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
