package com.ruoyi.employee.service;

import java.util.List;
import com.ruoyi.employee.domain.SysEmployee;

/**
 * 员工人事信息Service接口
 * 
 * @author ruoyi
 * @date 2026-06-18
 */
public interface ISysEmployeeService 
{
    /**
     * 查询员工人事信息
     * 
     * @param employeeId 员工人事信息主键
     * @return 员工人事信息
     */
    public SysEmployee selectSysEmployeeByEmployeeId(Long employeeId);

    /**
     * 查询员工人事信息列表
     * 
     * @param sysEmployee 员工人事信息
     * @return 员工人事信息集合
     */
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee);

    /**
     * 新增员工人事信息
     * 
     * @param sysEmployee 员工人事信息
     * @return 结果
     */
    public int insertSysEmployee(SysEmployee sysEmployee);

    /**
     * 修改员工人事信息
     * 
     * @param sysEmployee 员工人事信息
     * @return 结果
     */
    public int updateSysEmployee(SysEmployee sysEmployee);

    /**
     * 批量删除员工人事信息
     * 
     * @param employeeIds 需要删除的员工人事信息主键集合
     * @return 结果
     */
    public int deleteSysEmployeeByEmployeeIds(Long[] employeeIds);

    /**
     * 删除员工人事信息信息
     * 
     * @param employeeId 员工人事信息主键
     * @return 结果
     */
    public int deleteSysEmployeeByEmployeeId(Long employeeId);
}
