package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public Course save (Course course){
        if(course.getId()==null) {
            em.persist(course);
        }
        else {
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id){
        em.remove(findById(id));
    }


    public void playWithEntityManager(){
        Course course1 = new Course("Web Services 1");
        em.persist(course1);
        Course course2 = new Course("Web Services 2");
        em.persist(course2);

        em.flush();

        em.detach(course2);
        course1.setName("Web Services 1 - updated");
        course2.setName("Web Services 2 - updated");

        em.refresh(course1);
        em.flush();
    }

}
