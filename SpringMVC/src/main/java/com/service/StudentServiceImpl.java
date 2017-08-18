package com.service;

import com.entity.Student;
import com.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by mr.wl on 2017/8/18
 */
@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper mapper;

    public int createStudent(Student student)throws Exception{
        return mapper.insertStudent(student);

    }
}
