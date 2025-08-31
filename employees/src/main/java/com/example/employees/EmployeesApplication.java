package com.example.employees;

//modelmapper is the package name that is in the dependancy.
//ModelMapper is the class name.

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);

	}
	@Bean
	//here the modelMapper is the method name.
	public ModelMapper modelMapper(){
		return new ModelMapper();

	}

}
