package com.ruoyi.employee.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employee.mapper.SysEmployeeMapper;
import com.ruoyi.employee.domain.SysEmployee;
import com.ruoyi.employee.service.ISysEmployeeService;

/**
 * 员工人事信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-18
 */
@Service
public class SysEmployeeServiceImpl implements ISysEmployeeService 
{
    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    /**
     * 查询员工人事信息
     * 
     * @param employeeId 员工人事信息主键
     * @return 员工人事信息
     */
    @Override
    public SysEmployee selectSysEmployeeByEmployeeId(Long employeeId)
    {
        return sysEmployeeMapper.selectSysEmployeeByEmployeeId(employeeId);
    }

    /**
     * 查询员工人事信息列表
     * 
     * @param sysEmployee 员工人事信息
     * @return 员工人事信息
     */
    @Override
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.selectSysEmployeeList(sysEmployee);
    }

    /**
     * 新增员工人事信息
     * 
     * @param sysEmployee 员工人事信息
     * @return 结果
     */
    @Override
    public int insertSysEmployee(SysEmployee sysEmployee)
    {
        sysEmployee.setCreateTime(DateUtils.getNowDate());
        return sysEmployeeMapper.insertSysEmployee(sysEmployee);
    }

    /**
     * 修改员工人事信息
     * 
     * @param sysEmployee 员工人事信息
     * @return 结果
     */
    @Override
    public int updateSysEmployee(SysEmployee sysEmployee)
    {
        sysEmployee.setUpdateTime(DateUtils.getNowDate());
        return sysEmployeeMapper.updateSysEmployee(sysEmployee);
    }

    /**
     * 批量删除员工人事信息
     * 
     * @param employeeIds 需要删除的员工人事信息主键
     * @return 结果
     */
    @Override
    public int deleteSysEmployeeByEmployeeIds(Long[] employeeIds)
    {
        return sysEmployeeMapper.deleteSysEmployeeByEmployeeIds(employeeIds);
    }

    /**
     * 删除员工人事信息信息
     * 
     * @param employeeId 员工人事信息主键
     * @return 结果
     */
    @Override
    public int deleteSysEmployeeByEmployeeId(Long employeeId)
    {
        return sysEmployeeMapper.deleteSysEmployeeByEmployeeId(employeeId);
    }
}
