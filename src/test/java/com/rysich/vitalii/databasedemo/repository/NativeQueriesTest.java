package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class NativeQueriesTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void native_queries_basic() {
        Query query = em.createNativeQuery("select * from Course", Course.class);
        List<Course> list = query.getResultList();
        logger.info("native_queries_basic -> {}", list);
    }

    @Test
    void native_queries_with_parameter() {
        Query query = em.createNativeQuery("select * from Course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List<Course> list = query.getResultList();
        logger.info("native_queries_with_parameter -> {}", list);
    }

    @Test
    void native_queries_with_named_parameter() {
        Query query = em.createNativeQuery("select * from Course where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List<Course> list = query.getResultList();
        logger.info("native_queries_with_named_parameter -> {}", list);
    }

    @Test
    @Transactional
    void native_queries_to_update() {
        Query query = em.createNativeQuery("UPDATE Course SET last_updated_date = CURRENT_TIMESTAMP", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("native_queries_to_update -> {}", noOfRowsUpdated);
    }


}
