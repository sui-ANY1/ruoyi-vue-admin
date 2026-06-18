package com.ruoyi.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component("employeeStatTask")
public class EmployeeStatTask {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void countByDept() {
        String sql = "SELECT d.dept_name, COUNT(e.employee_id) AS emp_count " +
                "FROM sys_dept d " +
                "LEFT JOIN sys_employee e ON d.dept_id = e.dept_id AND e.status = '0' " +
                "GROUP BY d.dept_id, d.dept_name";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : list) {
            System.out.println("部门：" + row.get("dept_name") + "，在职人数：" + row.get("emp_count"));
        }
        System.out.println("----- 员工统计完成 -----");
    }
}