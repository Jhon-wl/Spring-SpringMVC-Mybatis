package com.service;

import com.entity.Student;

/**
 * Create by mr.wl on 2017/8/18
 */
public interface StudentService {
    //插入学生信息
    int createStudent(Student student) throws Exception;
}
