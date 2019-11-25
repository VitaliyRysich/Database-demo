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

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;
    @Autowired
    EntityManager em;

    @Test
    void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("Course1", course.getName());
    }

    @Test
    @Transactional //cahce test
    void findById_firstLevelCacheDemo() {
        Course course = repository.findById(10001L);
        logger.info("First course retrieved: {}", course);
        Course course1 = repository.findById(10001L);
        logger.info("First course retrieved again: {}", course1);
        assertEquals("Course1", course.getName());
        assertEquals("Course1", course1.getName());
    }

    @Test
    @DirtiesContext
    void deleteById_basic(){
        repository.deleteById(10001L);
        assertNull(repository.findById(10001L));
    }

    @Test
    @DirtiesContext
    void save_basic(){
        Course course = repository.findById(10001L);
        assertEquals("Course1", course.getName());

        course.setName("Course-update");
        repository.save(course);

        Course course1 = repository.findById(10001L);
        assertEquals("Course-update", course1.getName());
    }

    @Test
    @DirtiesContext
    void playWithEntityManager(){
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    void retrieveReviewForCourse(){
        Course course = repository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    void retrieveCourseForReview(){
        Review review = em.find(Review.class, 40001L);
        logger.info("{}", review.getCourse());
    }


}