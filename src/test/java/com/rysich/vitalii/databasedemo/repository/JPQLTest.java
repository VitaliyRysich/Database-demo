package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.DemoApplication;
import com.rysich.vitalii.databasedemo.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void jpql_basic() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List<Course> list = query.getResultList();
        logger.info("jpql_basic -> {}", list);
    }

    @Test
    void jpql_typed() {
        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
        List<Course> list = query.getResultList();
        logger.info("jpql_typed -> {}", list);
    }

    @Test
    void jpql_where() {
        TypedQuery<Course> query =
                em.createNamedQuery("query_get_Course", Course.class);
        List<Course> list = query.getResultList();
        logger.info("jpql_where -> {}", list);
    }



}