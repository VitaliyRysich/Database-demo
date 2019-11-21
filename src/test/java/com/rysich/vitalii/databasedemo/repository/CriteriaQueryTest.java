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
import javax.persistence.criteria.*;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void all_courses() {
        //select c from Course c
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> list = query.getResultList();
        logger.info("Typed query -> {}", list);
    }

    @Test
    void all_courses_having_Java() {
        //select c From Course where c.students is empty
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        Predicate likeJava = criteriaBuilder.like(courseRoot.get("name"), "%Java");
        criteriaQuery.where(likeJava);

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> list = query.getResultList();
        logger.info("Typed query -> {}", list);
    }

    @Test
    void all_courses_without_students() {
        //select c From Course where c.students is empty
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        Predicate likeJava = criteriaBuilder.isEmpty(courseRoot.get("students"));
        criteriaQuery.where(likeJava);

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> list = query.getResultList();
        logger.info("Typed query -> {}", list);
    }

    @Test
    void join() {
        //select c From Course c join c.students s
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students");
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> list = query.getResultList();
        logger.info("Typed query -> {}", list);
    }

    @Test
    void left_join() {
        //select c From Course c left join c.students s
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> list = query.getResultList();
        logger.info("Typed query -> {}", list);
    }

}