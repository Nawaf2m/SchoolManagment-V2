package com.example.schoolmanagment.Service;

import com.example.schoolmanagment.ApiResponse.ApiException;
import com.example.schoolmanagment.Model.Course;
import com.example.schoolmanagment.Model.Student;
import com.example.schoolmanagment.Model.Teacher;
import com.example.schoolmanagment.Repository.CourseRepository;
import com.example.schoolmanagment.Repository.StudentRepository;
import com.example.schoolmanagment.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Course> get(){
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()){
            throw new ApiException("The list is empty");
        }

        return courses;
    }

    public void add(Course course){
        Course oldCourse = courseRepository.findCourseById(course.getId());

        if (oldCourse != null){
            throw new ApiException("Course already exist");
        }

        courseRepository.save(course);
    }

    public void update(Integer id, Course course){
        Course oldCourse = courseRepository.findCourseById(id);

        if (oldCourse == null){
            throw new ApiException("Course not found");
        }

        oldCourse.setName(course.getName());

        courseRepository.save(oldCourse);
    }

    public void delete(Integer id){
        Course oldCourse = courseRepository.findCourseById(id);

        if (oldCourse == null){
            throw new ApiException("Course not found");
        }

        courseRepository.delete(oldCourse);
    }

    public void assignTeacherToCourse(Integer teacher_id, Integer course_id){
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);

        if (teacher == null){
            throw new ApiException("Teacher not found");
        }

        Course course = courseRepository.findCourseById(course_id);

        if (course == null){
            throw new ApiException("Course not found");
        }

        course.setTeacher(teacher);

        courseRepository.save(course);
    }

    public void assignStudentToCourse(Integer student_id, Integer course_id){
        Student student = studentRepository.findStudentById(student_id);

        if (student == null){
            throw new ApiException("Student not found");
        }

        Course course = courseRepository.findCourseById(course_id);

        if (course == null){
            throw new ApiException("Course not found");
        }

        course.getStudents().add(student);
        student.getCourses().add(course);

        courseRepository.save(course);
        studentRepository.save(student);
    }

    public String getTeacherName(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);

        if (course == null){
            throw new ApiException("Course not found");
        }

        if (course.getTeacher() == null){
            throw new ApiException("Teacher not assigned to this course");
        }

        return course.getTeacher().getName();
    }

    public Set<Student> getStudentsByCourseId(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);

        if (course == null){
            throw new ApiException("Course not found");
        }

        return course.getStudents();
    }
}