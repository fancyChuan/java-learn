package com.fancychuan.onemany.mapper;

import com.fancychuan.onemany.model.Classes;

public interface ClassesMapper {

    public Classes selectClassById(Integer id) throws Exception;

    public Classes selectClassAndStudentsById(Integer id) throws Exception;
}
