package com.ctguqmx.recruit.dao;

import com.ctguqmx.recruit.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> selectAll();
    int countAllByGroup(String group);
    int countAllBySex(String sex,String group);
    int countAllByMajor(String major,String group);
    List<Student> findByName(String studentName);
    Student findByNameAndSIdAndCode(String studentName,String studentId,String code);
    Student findById(Integer id);
    void deleteById(Integer id);
    void updateById(Student student);
    void updateCodeByTel(String code,String tel);
    void add(Student student);
    Student findByStudentId(String sid);
    Student findByTel(String tel);
    List<Student> findByGroup(String group);

    Student findStudentByNameAndIdAndTel(String studentName, String studentId, String tel);
}
