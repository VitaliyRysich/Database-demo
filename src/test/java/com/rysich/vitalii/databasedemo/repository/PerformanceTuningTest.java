package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.DemoApplication;
import com.rysich.vitalii.databasedemo.entity.Course;
import com.rysich.vitalii.databasedemo.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void creatingNPlusOneProblem() {//We retrieve Students from database a lot of times
        List<Course> courses = em
                .createNamedQuery("query_get_all_courses", Course.class)
                .getResultList();
        for(Course course: courses){
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");
        List<Course> courses = em
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        for(Course course: courses){
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_JoinFetch() {

        List<Course> courses = em
                .createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        for(Course course: courses){
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

}