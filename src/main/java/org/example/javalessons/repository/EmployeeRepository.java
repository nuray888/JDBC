package org.example.javalessons.repository;

import lombok.RequiredArgsConstructor;

import org.example.javalessons.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;
    public RowMapper<Employee> rowMapper=(rs, rowNum)->{
        Employee employee=new Employee();
        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setPosition(rs.getString("position"));
        employee.setSalary(rs.getBigDecimal("salary"));
        return employee;
    };

    public List<Employee> getAll(){
        var sql="SELECT * FROM employees";
        return jdbcTemplate.query(sql,rowMapper);
    }
    public Employee getById(Long id){
        var sql="SELECT * FROM employees WHERE id=?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
    public Employee save(Employee employee){
        var sql="INSERT INTO employees (name,position,salary) VALUES (?,?,?)";
        jdbcTemplate.update(sql,employee.getName(),employee.getPosition(),employee.getSalary());
        return employee;
    }

    public void batchUpdate(List<Employee> employees){
        var sql="INSERT INTO employees (name,position,salary) VALUES (?,?,?)";
        jdbcTemplate.batchUpdate(sql,employees, employees.size(), (ps,employee)->{
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getPosition());
            ps.setBigDecimal(3, employee.getSalary());
        });
    }
    public void deleteById(Long id){
        var sql="DELETE FROM employees WHERE id=?";
        jdbcTemplate.update(sql,id);
    }


}
