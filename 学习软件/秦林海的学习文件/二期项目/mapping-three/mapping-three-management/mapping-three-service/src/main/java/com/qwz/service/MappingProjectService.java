package com.qwz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qwz.mapper.MappingProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingProjectService {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    public PageInfo selectSuccessRegister(Integer userId,Integer currentPage,Integer pageSize) throws Exception {
        if(userId == null && userId == 0){
            throw new Exception("单位id不能为空");
        }
        PageHelper.startPage((currentPage-1)*pageSize,pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.selectSuccessRegister(userId);
        PageInfo<MappingProject> mappingProjectPageInfo = new PageInfo<>(mappingProjects);
        return mappingProjectPageInfo;
    }
}
