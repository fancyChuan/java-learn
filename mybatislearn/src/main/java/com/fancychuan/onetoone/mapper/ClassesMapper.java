package com.fancychuan.onetoone.mapper;

import com.fancychuan.onetoone.model.Classes;

public interface ClassesMapper {

    public Classes selectClassById(Integer id) throws Exception;

    public Classes selectClassAndStudentsById(Integer id) throws Exception;
}
