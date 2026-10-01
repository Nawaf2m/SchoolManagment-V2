package com.example.schoolmanagment.Service;

import com.example.schoolmanagment.ApiResponse.ApiException;
import com.example.schoolmanagment.Model.Course;
import com.example.schoolmanagment.Model.Student;
import com.example.schoolmanagment.Repository.CourseRepository;
import com.example.schoolmanagment.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> get(){
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()){
            throw new ApiException("The list is empty");
        }

        return students;
    }

    public void add(Student student){
        Student oldStudent = studentRepository.findStudentById(student.getId());

        if (oldStudent != null){
            throw new ApiException("Student already exist");
        }

        studentRepository.save(student);
    }

    public void update(Integer id, Student student){
        Student oldStudent = studentRepository.findStudentById(id);

        if (oldStudent == null){
            throw new ApiException("Student not found");
        }

        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());

        studentRepository.save(oldStudent);
    }

    public void delete(Integer id){
        Student oldStudent = studentRepository.findStudentById(id);

        if (oldStudent == null){
            throw new ApiException("Student not found");
        }

        studentRepository.delete(oldStudent);
    }

    public void changeMajor(Integer student_id, String major){
        Student student = studentRepository.findStudentById(student_id);

        if (student == null){
            throw new ApiException("Student not found");
        }

        for (Course course : student.getCourses()){
            course.getStudents().remove(student);
            courseRepository.save(course);
        }

        student.getCourses().clear();
        student.setMajor(major);

        studentRepository.save(student);
    }
}