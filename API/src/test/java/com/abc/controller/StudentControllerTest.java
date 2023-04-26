package com.abc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.abc.model.Student;
import com.abc.repository.StudentRepository;
import com.abc.service.StudentService;

//public class StudentControllerTest {
//
//	@Mock
//	StudentService studentService;
//	
//	@Mock
//	StudentRepository studentRepository;
//	
//	@InjectMocks
//	StudentController studentController;
//	
//	Student student;
//	Student student1;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		student = new Student();
//		student.setStudentId(12);
//		student.setStudentName("Abhishek");
//	}
//	
//	@Test 
//	public void addStudent_test() {
////		student1 = new Student();
////		student1.setStudentId(12);
////		student1.setStudentName("Abhishek");
//		when(studentService.saveStudent(student)).thenReturn(student1);
//		Student s=studentRepository.save(student);
//		assertEquals(s,student1);
//	}
//}

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

	Student student;
	Student studentId;
	List<Student> studentList;

	ObjectMapper objectMapper = new ObjectMapper();

	@InjectMocks
	StudentController studentController;
	
	@Mock
	StudentRepository studentRepository;

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Mock
	StudentService studentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		student = getstudent();
        
	}

	@Test
	public void saveStudentTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(student);
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);
		MvcResult mvcResult = mockMvc
				.perform(post("/add").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Student expectedOutputUser = objectMapper.readValue(expectedOutput, Student.class);
		assertEquals(expectedOutputUser.getStudentId(), student.getStudentId());
	}

	
	@Test
	public void deleteStudent_test() {
		when(studentService.deletStudent(Mockito.anyInt())).thenReturn(student.getStudentId());
		Integer id = studentController.deletStudent(student.getStudentId());
		System.out.println("id:" + id);
		assertEquals(student.getStudentId(), (Integer) id);

	}
 
	
	private Student getstudent() {
		Student student = new Student();
		student.setStudentId(45);
		student.setStudentName("yogesh");
		return student;

	}

	private List<Student> getList() {
		List<Student> studentList = new ArrayList<>();
		Student student = new Student();
		student.setStudentId(45);
		student.setStudentName("yogesh");

		Student student1 = new Student();
		student1.setStudentId(451);
		student1.setStudentName("Abhishak");

		studentList.add(student);
		studentList.add(student1);

		return studentList;

	} 
	
	@Test
	public void findAllStudentTest() throws Exception{
		
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);
		MvcResult mvcResult = mockMvc
				.perform(get("/get")).andExpect(status().isOk()).andReturn(); 
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void findAllStudentByIDTest() throws Exception{
		when(studentService.getById(0)).thenReturn(student);
		MvcResult mvcResult = mockMvc.perform(get("/getbyid/{studentId}")).andExpect(status().isOk()).andReturn();		
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Student expectedOutputUser = objectMapper.readValue(expectedOutput, Student.class);
		assertEquals(expectedOutputUser.getStudentId(), student.getStudentId());

	}

}