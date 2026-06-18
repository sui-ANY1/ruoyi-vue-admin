package com.ruoyi.employee.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工人事信息对象 sys_employee
 * 
 * @author ruoyi
 * @date 2026-06-18
 */
public class SysEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工主键ID */
    private Long employeeId;

    /** 所属部门ID */
    @Excel(name = "所属部门ID")
    private Long deptId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String employeeName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 岗位 */
    @Excel(name = "岗位")
    private String post;

    /** 薪资 */
    @Excel(name = "薪资")
    private BigDecimal salary;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryDate;

    /** 状态 0在职 1离职 */
    @Excel(name = "状态 0在职 1离职")
    private String status;

    /** 员工头像地址 */
    @Excel(name = "员工头像地址")
    private String avatar;

    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() 
    {
        return employeeName;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setPost(String post) 
    {
        this.post = post;
    }

    public String getPost() 
    {
        return post;
    }

    public void setSalary(BigDecimal salary) 
    {
        this.salary = salary;
    }

    public BigDecimal getSalary() 
    {
        return salary;
    }

    public void setEntryDate(Date entryDate) 
    {
        this.entryDate = entryDate;
    }

    public Date getEntryDate() 
    {
        return entryDate;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeId", getEmployeeId())
            .append("deptId", getDeptId())
            .append("employeeName", getEmployeeName())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("post", getPost())
            .append("salary", getSalary())
            .append("entryDate", getEntryDate())
            .append("status", getStatus())
            .append("avatar", getAvatar())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
