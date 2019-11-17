package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.entity.Course;
import com.rysich.vitalii.databasedemo.entity.Passport;
import com.rysich.vitalii.databasedemo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public Student save (Student student){
        if(student.getId()==null) {
            em.persist(student);
        }
        else {
            em.merge(student);
        }
        return student;
    }

    public void deleteById(Long id){
        em.remove(findById(id));
    }

    public void saveStudentWithPassport (){
        Passport passport = new Passport("E123456");
        em.persist(passport);
        Student student = new Student("Vitalii");
        student.setPassport(passport);
        em.persist(student);
    }



}
