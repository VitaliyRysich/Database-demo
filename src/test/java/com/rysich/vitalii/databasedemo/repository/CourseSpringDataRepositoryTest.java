package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.DemoApplication;
import com.rysich.vitalii.databasedemo.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent(){
        Optional<Course> courseOptional = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent(){
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundSpringRepository(){
        Course course = new Course("new Course");
        repository.save(course);
        course.setName("new Course - updated");
        repository.save(course);

        logger.info("{}", repository.findAll());
    }

    @Test
    public void sort(){
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        logger.info("Sorted courses:  {}", repository.findAll(sort));
    }
}