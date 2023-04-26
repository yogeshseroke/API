package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public List<Student> findByStudentName(String studentName);
	public List<Student> findByStudentNameAndStudentId(String StudentName,Integer StudentId);
}
