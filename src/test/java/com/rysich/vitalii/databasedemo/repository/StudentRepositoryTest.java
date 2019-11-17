package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.entity.Course;
import com.rysich.vitalii.databasedemo.entity.Passport;
import com.rysich.vitalii.databasedemo.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrievePassportAndStudentDetails() {
        Passport passport = em.find(Passport.class, 30001L);
        logger.info("passport -> {}", passport);
        logger.info("student -> {}", passport.getStudent());
    }

}