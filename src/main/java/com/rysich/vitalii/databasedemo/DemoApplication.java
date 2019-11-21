package com.rysich.vitalii.databasedemo;

import com.rysich.vitalii.databasedemo.entity.*;
import com.rysich.vitalii.databasedemo.repository.CourseRepository;
import com.rysich.vitalii.databasedemo.repository.EmployeeRepository;
import com.rysich.vitalii.databasedemo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeRepository.insert(new FullTimeEmployee("John", new BigDecimal(15000)));
		employeeRepository.insert(new PartTimeEmployee("Robert", new BigDecimal(50)));
		logger.info("Employees: {}", employeeRepository.retrieveAllEmployees());
	}

}
