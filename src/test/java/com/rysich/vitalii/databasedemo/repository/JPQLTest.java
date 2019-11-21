package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.DemoApplication;
import com.rysich.vitalii.databasedemo.entity.Course;
import com.rysich.vitalii.databasedemo.entity.Student;
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

    @Test
    void jpql_courses_without_students() {
        TypedQuery<Course> query =
                em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> list = query.getResultList();
        logger.info("Results -> {}", list);
    }

    @Test
    void jpql_courses_with_atlist_2_students() {
        TypedQuery<Course> query =
                em.createQuery("select c from Course c where size(c.students) >=2", Course.class);
        List<Course> list = query.getResultList();
        logger.info("Results -> {}", list);
    }

    @Test
    void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query =
                em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> list = query.getResultList();
        logger.info("Results -> {}", list);
    }

    @Test
    void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query =
                em.createQuery("select s from Student s where s.passport.number like '%12%'", Student.class);
        List<Student> list = query.getResultList();
        logger.info("Results -> {}", list);
    }

    @Test
    public void join(){
        Query query =
                em.createQuery("select c, s from Course c JOIN c.students s");
        List<Object[]> list = query.getResultList();
        logger.info("Results Size-> {}", list.size());
        for(Object[] result: list){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    public void left_join(){
        Query query =
                em.createQuery("select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> list = query.getResultList();
        logger.info("Results Size-> {}", list.size());
        for(Object[] result: list){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query =
                em.createQuery("select c, s from Course c, Student s");
        List<Object[]> list = query.getResultList();
        logger.info("Results Size-> {}", list.size());
        for(Object[] result: list){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

}