package com.student.student.management.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.student.management.model.Student;
import com.student.student.management.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public String viewStudents(Model model) {

        model.addAttribute(
                "students",
                service.getAllStudents());

        return "students";
    }

    @GetMapping("/add-student")
    public String addStudentPage(Model model) {

        model.addAttribute(
                "student",
                new Student());

        return "add-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(
            @ModelAttribute Student student) {

        service.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/delete-student/{id}")
public String deleteStudent(@PathVariable Integer id) {

    service.deleteStudent(id);

    return "redirect:/students";
}

@GetMapping("/edit-student/{id}")
public String editStudent(
        @PathVariable Integer id,
        Model model) {

    Student student = service.getStudentById(id);

    model.addAttribute("student", student);

    return "edit-student";
}

@GetMapping("/search")
public String searchStudents(
        @RequestParam String keyword,
        Model model) {

    model.addAttribute(
            "students",
            service.searchStudents(keyword));

    return "students";
}

@GetMapping("/students/sorted")
public String sortedStudents(Model model) {

    model.addAttribute(
            "students",
            service.getStudentsSortedByMarks());

    return "students";
}

@GetMapping("/export")
public void exportCsv(HttpServletResponse response)
        throws IOException {

    response.setContentType("text/csv");

    response.setHeader(
            "Content-Disposition",
            "attachment; filename=students.csv");

    PrintWriter writer =
            response.getWriter();

    writer.println(
            "ID,Name,Course,Marks");

    for(Student s :
            service.getAllStudents()) {

        writer.println(
                s.getId() + "," +
                s.getName() + "," +
                s.getCourse() + "," +
                s.getMarks());
    }

    writer.flush();
}
}