package com.myproject.service;

import com.myproject.model.Student;
import com.myproject.model.StudentCreateOrUpdateResponce;
import com.myproject.model.StudentRequest;
import com.myproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(StudentRequest studentRequest) {
        return studentRepository.getStudents(studentRequest);
    }

    public Student getStudent(String id) {
        return studentRepository.getStudent(Long.getLong(id));
    }

    public Student createStudent(Student student){
        return studentRepository.createStudent(student);
    }

    public Student updateStudent(Student student){
        return studentRepository.updateStudent(student);
    }

    public Student deleteStudent(String id){
        return studentRepository.deleteStudent(Long.parseLong(id));
    }

    public StudentCreateOrUpdateResponce canCreateOrUpdateStudent(Student student){
        StudentCreateOrUpdateResponce studentCreateOrUpdateResponce = new StudentCreateOrUpdateResponce(true);

        if(student.getRating() == null || student.getRating() < 0 || student.getRating() > 10){
            studentCreateOrUpdateResponce.setCan(false);
            studentCreateOrUpdateResponce.addError("Rating must be in [0, 10] and can't be null.");
        }
        if(student.getFirstName() == null || student.getFirstName().length() == 0 || student.getFirstName().length() > 100){
            studentCreateOrUpdateResponce.setCan(false);
            studentCreateOrUpdateResponce.addError("First name size must be in [1, 100] and can't be null.");
        }
        if(student.getLastName() == null || student.getLastName().length() == 0 || student.getLastName().length() > 100){
            studentCreateOrUpdateResponce.setCan(false);
            studentCreateOrUpdateResponce.addError("Last name size must be in [1, 100] and can't be null.");
        }
        if(student.getAge() == null || student.getAge() < 1 || student.getAge() > 120){
            studentCreateOrUpdateResponce.setCan(false);
            studentCreateOrUpdateResponce.addError("Age must be in [1, 120] and can't be null.");
        }
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setMail(student.getMail());
        if(studentRepository.getStudents(studentRequest).size() > 0){
            studentCreateOrUpdateResponce.setCan(false);
            studentCreateOrUpdateResponce.addError("Student with this mail already exists.");
        }
        if(!student.getMail().contains("@")){
            studentCreateOrUpdateResponce.setCan(false);
            studentCreateOrUpdateResponce.addError("Invalid mail (must contain @ and can't be null).");
        }

        return studentCreateOrUpdateResponce;
    }
}
