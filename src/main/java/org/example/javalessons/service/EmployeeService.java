package org.example.javalessons.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.javalessons.entity.Employee;
import org.example.javalessons.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public List<Employee> findAll(){
        log.info("ActionLog.findAllEmployee.start");

        List<Employee> all = employeeRepository.getAll();
        log.info("ActionLog.findAllEmployee.start");
return all;
    }
    public void save(Employee employee){
        log.info("ActionLog.saveEmployee.start with id: {}",employee.getName());
        employeeRepository.save(employee);
        log.info("ActionLog.saveEmployee.end with id: {}",employee.getName());
    }
    public Employee findById(Long id){
        log.info("ActionLog.findByIdEmployee.start with id: {}",id);
        Employee employee = employeeRepository.getById(id);
        log.info("ActionLog.findByIdEmployee.end with id: {}",id);
        return employee;
    }
    public void update(List<Employee> employees){
        log.info("ActionLog.bachInsertEmployee.start");
        employeeRepository.batchUpdate(employees);
        log.info("ActionLog.bachInsertEmployee.end");

    }
    public void deleteById(Long id){
        log.info("ActionLog.DeleteEmployee.start with id: {}",id);
        employeeRepository.deleteById(id);
        log.info("ActionLog.DeleteEmployee.end with id: {}",id);

    }
    @PostConstruct
    public void init(){
        var employee=Employee.builder()
                .name("John")
                .position("Developer")
                .salary(BigDecimal.valueOf(1000))
                .build();
//        employeeRepository.save(employee);
        save(employee);
        deleteById(10L);
    }
}
