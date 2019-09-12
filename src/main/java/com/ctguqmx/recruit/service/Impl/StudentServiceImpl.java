package com.ctguqmx.recruit.service.Impl;

import com.ctguqmx.recruit.dao.StudentMapper;
import com.ctguqmx.recruit.pojo.Student;
import com.ctguqmx.recruit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> FindAll() {
        return studentMapper.selectAll();
    }

    @Override
    public List<Student> FindByName(String studentName) {
        return studentMapper.findByName(studentName);
    }

    @Override
    public void DeleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public Student FindById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void Update(Student student) {
        studentMapper.updateById(student);
    }

    @Override
    public boolean AddStudent(Student student) {
        studentMapper.add(student);
        return true;
    }

    @Override
    public Student selectBySid(Student student) {
        System.out.println(studentMapper.findByStudentId(student.getStudentId()));
        return studentMapper.findByStudentId(student.getStudentId());
    }

    @Override
    public int findNum(String group) {
        return studentMapper.countAllByGroup(group);
    }

    @Override
    public int findNUmByMajor(String major,String group) {
        return studentMapper.countAllByMajor(major,group);
    }

    @Override
    public int findNumBySex(String s, String group) {
        return studentMapper.countAllBySex(s,group);
    }

    @Override
    public Student FindByNameAndSIdAndCode(String studentName, String studentId,String code) {
        return studentMapper.findByNameAndSIdAndCode(studentName,studentId,code);
    }

    @Override
    public void updateCodeByTel(String code,String tel) {
        studentMapper.updateCodeByTel(code,tel);
    }

    @Override
    public List<Student> findByGroup(String group) {
        return studentMapper.findByGroup(group);
    }

    @Override
    public Student selectByTel(Student student) {
        return studentMapper.findByTel(student.getTel());
    }

    @Override
    public Student findStudentByNameAndIdAndTel(String studentName, String studentId, String tel) {
        return studentMapper.findStudentByNameAndIdAndTel(studentName,studentId,tel);
    }
}
