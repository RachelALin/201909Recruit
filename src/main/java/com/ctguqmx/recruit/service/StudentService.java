package com.ctguqmx.recruit.service;

import com.ctguqmx.recruit.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> FindAll();

    List<Student> FindByName(String studentName);

    void DeleteById(Integer id);

    Student FindById(Integer id);

    void Update(Student student);

    boolean AddStudent(Student student);

    Student selectBySid(Student student);

    int findNum(String group);

    int findNUmByMajor(String major,String group);

    int findNumBySex(String s, String group);

    Student FindByNameAndSIdAndCode(String studentName, String studentId,String code);

    void updateCodeByTel(String code,String tel);

    List<Student> findByGroup(String group);

    Student selectByTel(Student student);

    Student findStudentByNameAndIdAndTel(String studentName, String studentId, String tel);
}
