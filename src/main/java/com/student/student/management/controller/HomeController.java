package com.student.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.student.student.management.service.StudentService;

@Controller
public class HomeController {

   @Autowired
private StudentService service;

@GetMapping("/")
public String dashboard(Model model) {

    model.addAttribute(
            "totalStudents",
            service.getTotalStudents());

    model.addAttribute(
            "averageMarks",
            service.getAverageMarks());

    model.addAttribute(
        "highestMarks",
        service.getHighestMarks());

model.addAttribute(
        "topStudent",
        service.getTopStudentName());

    return "index";
}

}