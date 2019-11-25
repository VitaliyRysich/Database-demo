package com.rysich.vitalii.databasedemo.repository;

import com.rysich.vitalii.databasedemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Long id);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);
    @Query("select c from Course c where name like '%Course%'")
    List<Course> coursesWithNameCourse();

    @Query(value = "select * from Course c where name like '%Course%'", nativeQuery = true)
    List<Course> coursesWithNameCourseUsingNativeQuery();

    @Query(name = "query_get_Course")
    List<Course> coursesWithNameCourseUsingNamedQuery();
}
