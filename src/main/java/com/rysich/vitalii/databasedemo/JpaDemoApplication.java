package com.rysich.vitalii.databasedemo;

import com.rysich.vitalii.databasedemo.entity.Person;
import com.rysich.vitalii.databasedemo.jdbc.PersonJdbcDAO;
import com.rysich.vitalii.databasedemo.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJpaRepository dao;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Person id=10002 -> {}", dao.findById(10002));

		logger.info("All users -> {}", dao.findAll());


		dao.deleteById(10002);
		logger.info("Inserting -> {}", dao.insert(new Person( "Dmytro", "Zhytomyr", new Date())));
		logger.info("updating 10003 -> {}", dao.update(new Person(10003, "Dmytro", "Wroclaw", new Date())));
	}
}
