package com.abc.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.abc.model.Student;
import com.abc.repository.StudentRepository;
import com.abc.service.StudentService;

@SpringBootTest
public class ServiceTest {
	
	Student student2;
	String studentName;
	String student12 = "Deleted Successfully";
	List<Student> list;
	Integer studentId;

	@InjectMocks
	StudentService studentService;

	@Mock
	StudentRepository repo;

	@BeforeEach
	void setup() {
		Student student = getStudent();
		list = new ArrayList();
		studentId = 12;
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);
		Student student2 = new Student();
		student2.setStudentName("abhishak");
		student2.setStudentId(12);
		list.add(student1);
		list.add(student2);
	}
	@Test
	public void saveStudent_test() {
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);
		when(repo.save(Mockito.any(Student.class))).thenReturn(student1);
		student2 = studentService.saveStudent(student1);
		assertEquals(student1.toString(), student2.toString());
	}
	@Test
	public void getStudent_test() {
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);
		when(repo.findAll()).thenReturn(list);
		List<Student> studentList = studentService.getStudent();
		assertEquals(list, studentList);
	}
	@Test
	public void updateStudent_test() {
		Student student = new Student();
		student.setStudentName("abhishak");
		student.setStudentId(12);
		when(repo.findById(student.getStudentId())).thenReturn(Optional.of(student));
		student.setStudentId(13);
		student.setStudentName("Ram");
		when(repo.save(Mockito.any(Student.class))).thenReturn(student);
		Student updatedStudent = studentService.updateStudent(student, studentId);
		assertEquals(student.toString(), updatedStudent.toString());
	}
	@Test
	void deletStudent_Test() {
		when(repo.findByStudentName(studentName)).thenReturn(list, null);
		Integer id = studentService.deletStudent(studentId);
		assertEquals(id.toString(), studentId.toString());
	}  
	@Test
	void getById_Test() { 
		Student stu = new Student();
		stu.setStudentId(12);
		stu.setStudentName("Abhishek");
		when(repo.findById(studentId)).thenReturn(Optional.of(stu));
		Student id = studentService.getById(studentId);
		assertEquals(id, stu);  
	}
	
	private Student getStudent() {
		Student student = new Student();
		student.setStudentName("abhishak");
		student.setStudentId(12);
		return student;
	}
}
