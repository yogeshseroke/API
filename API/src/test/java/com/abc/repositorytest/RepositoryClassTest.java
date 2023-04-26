package com.abc.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.model.Student;
import com.abc.repository.StudentRepository;

@SpringBootTest
public class RepositoryClassTest {

	Student student;
	@Autowired
	StudentRepository studentRepository;

	@BeforeEach
	void setup() {
		student = getStudent();
	}

	@Test
	void addStudent_test() {
		Student st = getStudent();
		Student st2 = new Student();
		st2.setStudentId(9);
		st2.setStudentName("kanu");
		Student saveStudent = studentRepository.save(st2);
		assertEquals(st2.toString(), saveStudent.toString());
	}

	@Test
	void allGetStudent_test() {
		Student student = new Student();
		student.setStudentName("kanu");
		student.setStudentId(3);
		studentRepository.save(student);
		List<Student> listStudent = studentRepository.findAll();
		assertThat(listStudent).isNotNull(); 
		 assertThat(listStudent.size()).isEqualTo(listStudent.size());
	}

	@Test
	void deletStudent_test() {
		studentRepository.save(student);
		studentRepository.deleteById(student.getStudentId());
		java.util.Optional<Student> studentOptional = studentRepository.findById(student.getStudentId());
		assertThat(studentOptional).isEmpty();
	}

	@Test
	void updateStudent_test() {
		studentRepository.save(student);
		Student saveStudent = studentRepository.findById(student.getStudentId()).get();
		System.out.println("student:"+saveStudent.toString());
		saveStudent.setStudentId(2);
		saveStudent.setStudentName("Ram");
		Student updatedStudent = studentRepository.save(saveStudent);
		assertEquals(saveStudent.toString(), updatedStudent.toString());
	}

	private Student getStudent() {
		Student student = new Student();
		student.setStudentName("kanu");
		student.setStudentId(2);
		return student;
	}
}
