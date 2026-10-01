package com.example.schoolmanagment.Controller;

import com.example.schoolmanagment.ApiResponse.ApiResponse;
import com.example.schoolmanagment.Model.Student;
import com.example.schoolmanagment.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(studentService.get());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Student student){
        studentService.add(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Student student){
        studentService.update(id,student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        studentService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/change-major/{student_id}/{major}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer student_id, @PathVariable String major){
        studentService.changeMajor(student_id, major);
        return ResponseEntity.status(200).body(new ApiResponse("Student major changed successfully"));
    }
}