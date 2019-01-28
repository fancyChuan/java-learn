package com.fancychuan.onemany.mapper;

import com.fancychuan.onemany.model.Student;
import com.fancychuan.onemany.model.StudentCourseLink;

import java.util.List;

public interface StudentMapper {

    public List<Student> selectStudentCourse() throws Exception;

    public void deleteStudentCourseById(StudentCourseLink scLink) throws Exception;

}
