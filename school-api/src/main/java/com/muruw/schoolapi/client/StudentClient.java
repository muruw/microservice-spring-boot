package com.muruw.schoolapi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.muruw.schoolapi.Student;

@FeignClient(name = "student-service", url = "${application.config.student-api.url}")
public interface StudentClient {

    @GetMapping("/school/{schoolId}") List<Student> findAllStudentsBySchool(@PathVariable Integer schoolId);
}
