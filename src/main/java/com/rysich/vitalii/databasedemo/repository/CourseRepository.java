package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.entity.Course;
import com.rysich.vitalii.databasedemo.entity.Review;
import com.rysich.vitalii.databasedemo.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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


    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        for(Review review : reviews){
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }

    public void addHardcodeReviewsForCourse() {
        Course course = findById(10003L);
        logger.info("course.getReview() - {}", course.getReviews());
        Review review1 = new Review(ReviewRating.FIVE, "Great");
        Review review2 = new Review(ReviewRating.FIVE, "GOOD");

    }
}
