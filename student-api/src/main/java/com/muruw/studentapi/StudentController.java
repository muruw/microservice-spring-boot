package com.muruw.studentapi;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> findAllStudents() {
        List<StudentDto> allStudents = studentService.findAllStudents().stream()
                .map(StudentDto::fromStudent)
                .toList();
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<StudentDto>> findAllStudents(@PathVariable Integer schoolId) {
        List<StudentDto> allStudents = studentService.findAllBySchoolId(schoolId).stream()
                .map(StudentDto::fromStudent)
                .toList();
        return ResponseEntity.ok(allStudents);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(
            @RequestBody StudentDto student
    ) {
        studentService.saveStudent(student.toStudent());
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentDto {

        private String firstname;
        private String lastname;
        private String email;
        private Integer schoolId;

        public Student toStudent() {
            return Student.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .email(email)
                    .schoolId(schoolId)
                    .build();
        }

        public static StudentDto fromStudent(Student student) {
            return builder()
                    .firstname(student.getFirstname())
                    .lastname(student.getLastname())
                    .email(student.getEmail())
                    .schoolId(student.getSchoolId())
                    .build();
        }
    }
}
