package com.myproject.repository;

import com.myproject.model.Student;
import com.myproject.model.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository implements org.springframework.data.repository.Repository<Student, Long> {

    @Autowired
    private EntityManager entityManager;

    public List<Student> getStudents(StudentRequest studentRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(studentRequest.getName()))
            predicates.add(builder.like(root.get("firstName"), new StringBuilder("%").append(studentRequest.getName()).append("%").toString()));
        if (StringUtils.isNotEmpty(studentRequest.getLastName()))
            predicates.add(builder.like(root.get("lastName"), new StringBuilder("%").append(studentRequest.getLastName()).append("%").toString()));
        if (StringUtils.isNotEmpty(studentRequest.getMail()))
            predicates.add(builder.like(root.get("mail"), new StringBuilder("%").append(studentRequest.getMail()).append("%").toString()));
        if (studentRequest.getMinAge() != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("age"), studentRequest.getMinAge()));
        if (studentRequest.getMaxAge() != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("age"), studentRequest.getMaxAge()));
        if (studentRequest.getMinRating() != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("rating"), studentRequest.getMinRating()));
        if (studentRequest.getMaxRating() != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("rating"), studentRequest.getMaxRating()));

        Predicate[] filterPredicates = new Predicate[predicates.size()];
        predicates.toArray(filterPredicates);
        query.select(root).where(filterPredicates);

        return entityManager.createQuery(query).getResultList();
    }

    public Student getStudent(String id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        query.select(root).where(builder.equal(root.get("id"), id));

        return entityManager.createQuery(query).getResultStream().findFirst().orElse(null);
    }

    public Student createStudent(Student student) {
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }

    public Student updateStudent(Student student) {
        Student updateStudent = entityManager.find(Student.class, student.getId());
        if (updateStudent == null)
            return null;
        if (StringUtils.isNotEmpty(student.getFirstName()))
            updateStudent.setFirstName(student.getFirstName());
        if (StringUtils.isNotEmpty(student.getLastName()))
            updateStudent.setLastName(student.getLastName());
        if (StringUtils.isNotEmpty(student.getMail()))
            updateStudent.setMail(student.getMail());
        if (student.getAge() != null)
            updateStudent.setAge(student.getAge());
        if (student.getRating() != null)
            updateStudent.setRating(student.getRating());

        return entityManager.merge(updateStudent);
    }

    public Student deleteStudent(Long id) {
        Student deleteStudent = entityManager.find(Student.class, id);
        if (deleteStudent == null)
            return null;

        entityManager.remove(deleteStudent);
        return deleteStudent;
    }
}
