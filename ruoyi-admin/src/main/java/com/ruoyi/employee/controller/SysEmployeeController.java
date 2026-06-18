package com.ruoyi.employee.controller;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.employee.domain.SysEmployee;
import com.ruoyi.employee.service.ISysEmployeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工人事信息Controller
 * 
 * @author ruoyi
 * @date 2026-06-18
 */
@RestController
@RequestMapping("/employee/员工管理")
public class SysEmployeeController extends BaseController
{
    @Autowired
    private ISysEmployeeService sysEmployeeService;

    /**
     * 查询员工人事信息列表
     */
    @PreAuthorize("@ss.hasPermi('employee:员工管理:list')")
    @DataScope(deptAlias = "e")
    @GetMapping("/list")
    public TableDataInfo list(SysEmployee sysEmployee)
    {
        startPage();
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工人事信息列表
     */
    @PreAuthorize("@ss.hasPermi('employee:员工管理:export')")
    @Log(title = "员工人事信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysEmployee sysEmployee)
    {
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        ExcelUtil<SysEmployee> util = new ExcelUtil<SysEmployee>(SysEmployee.class);
        util.exportExcel(response, list, "员工人事信息数据");
    }

    /**
     * 获取员工人事信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('employee:员工管理:query')")
    @GetMapping(value = "/{employeeId}")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        return success(sysEmployeeService.selectSysEmployeeByEmployeeId(employeeId));
    }

    /**
     * 新增员工人事信息
     */
    @PreAuthorize("@ss.hasPermi('employee:员工管理:add')")
    @Log(title = "员工人事信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysEmployee sysEmployee)
    {
        return toAjax(sysEmployeeService.insertSysEmployee(sysEmployee));
    }

    /**
     * 修改员工人事信息
     */
    @PreAuthorize("@ss.hasPermi('employee:员工管理:edit')")
    @Log(title = "员工人事信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysEmployee sysEmployee)
    {
        return toAjax(sysEmployeeService.updateSysEmployee(sysEmployee));
    }

    /**
     * 删除员工人事信息
     */
    @PreAuthorize("@ss.hasPermi('employee:员工管理:remove')")
    @Log(title = "员工人事信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(sysEmployeeService.deleteSysEmployeeByEmployeeIds(employeeIds));
    }
}
