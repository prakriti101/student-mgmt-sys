package com.student.student.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.student.management.model.Student;
import com.student.student.management.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public List<Student> searchStudents(String keyword) {
    return repository.findByNameContainingIgnoreCase(keyword);
}

    public void deleteStudent(Integer id) {
    repository.deleteById(id);
}
public Student getStudentById(Integer id) {
    return repository.findById(id).orElse(null);
}

public long getTotalStudents() {
    return repository.count();
}

public Double getAverageMarks() {

    List<Student> students = repository.findAll();

    if (students.isEmpty()) {
        return 0.0;
    }

    double sum = 0;

    for (Student s : students) {
        sum += s.getMarks();
    }

    return sum / students.size();
}

public double getHighestMarks() {

    List<Student> students = repository.findAll();

    double highest = 0;

    for(Student s : students) {

        if(s.getMarks() > highest) {
            highest = s.getMarks();
        }
    }

    return highest;
}

public String getTopStudentName() {

    List<Student> students = repository.findAll();

    if(students.isEmpty()) {
        return "No Students";
    }

    Student topStudent = students.get(0);

    for(Student s : students) {

        if(s.getMarks() > topStudent.getMarks()) {
            topStudent = s;
        }
    }

    return topStudent.getName();
}

public List<Student> getStudentsSortedByMarks() {

    List<Student> students = repository.findAll();

    students.sort(
            (s1, s2) ->
                    Double.compare(
                            s2.getMarks(),
                            s1.getMarks()));

    return students;
}

}