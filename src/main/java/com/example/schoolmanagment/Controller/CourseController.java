package com.example.schoolmanagment.Controller;

import com.example.schoolmanagment.ApiResponse.ApiResponse;
import com.example.schoolmanagment.Model.Course;
import com.example.schoolmanagment.Service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(courseService.get());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Course course){
        courseService.add(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Course course){
        courseService.update(id,course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        courseService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
    }

    @PutMapping("/assign/{teacher_id}/{course_id}")
    public ResponseEntity<?> assignTeacherToCourse(@PathVariable Integer teacher_id, @PathVariable Integer course_id){
        courseService.assignTeacherToCourse(teacher_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher assigned to course successfully"));
    }

    @PutMapping("/assign-student/{student_id}/{course_id}")
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Integer student_id, @PathVariable Integer course_id){
        courseService.assignStudentToCourse(student_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Student assigned to course successfully"));
    }

    @GetMapping("/teacher-name/{course_id}")
    public ResponseEntity<?> getTeacherName(@PathVariable Integer course_id){
        return ResponseEntity.status(200).body(courseService.getTeacherName(course_id));
    }

    @GetMapping("/students/{course_id}")
    public ResponseEntity<?> getStudentsByCourseId(@PathVariable Integer course_id){
        return ResponseEntity.status(200).body(courseService.getStudentsByCourseId(course_id));
    }
}