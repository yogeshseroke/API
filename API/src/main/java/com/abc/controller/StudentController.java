package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.model.Student;
import com.abc.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/add")
	public Student addStudent(@RequestBody Student student)
	{
		return studentService.saveStudent(student);
	}
  
	@GetMapping("/get")
	public List<Student> findAllStudent()
	{ 
		return studentService.getStudent();
	}
	@GetMapping("/getbyid/{studentId}")
	public Student findAllStudentByID(@PathVariable Integer studentId)
	{
		return studentService.getById(studentId);
	}
	
	@GetMapping("/getbyname/{studentName}")
	public List<Student> findAllStudentbyName(@PathVariable String studentName) {
		return  studentService.getByName(studentName);
		
	}

	@PutMapping("/update/{id}")
	public Student updateStudent(@RequestBody Student student, @PathVariable int id)
	{
		return studentService.updateStudent(student,id);
	}
	@DeleteMapping("/Delete/{id}")
	public int deletStudent(@PathVariable int id)
	{
	int studentid= studentService.deletStudent(id);
		 return studentid;
	}
}
