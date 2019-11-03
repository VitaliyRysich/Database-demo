package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.DemoApplication;
import com.rysich.vitalii.databasedemo.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Test
    void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("Course1", course.getName());
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




}