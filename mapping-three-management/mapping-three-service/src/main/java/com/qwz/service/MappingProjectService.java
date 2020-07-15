package com.qwz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qwz.base.BaseService;
import com.qwz.mapper.MappingProjectMapper;
import com.qwz.model.MappingProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    public PageInfo selectSuccessRegister(Integer userId,Integer currentPage,Integer pageSize,String searchName) throws Exception {
        if(userId == null && userId == 0){
            throw new Exception("单位id不能为空");
        }
        PageHelper.startPage((currentPage-1)*pageSize,pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.selectSuccessRegister(userId,searchName);
        PageInfo<MappingProject> mappingProjectPageInfo = new PageInfo<>(mappingProjects);
        return mappingProjectPageInfo;
    }
}
