package com.crud.application.Employee.services;


import com.crud.application.Employee.entities.Employee;
import com.crud.application.Employee.controllers.customNOEmployeeFoundException;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> getEmployees();

    public Employee fetchEmployee(Integer id) throws customNOEmployeeFoundException;

    public void deleteEmployeeById(Integer employeeId);

    public String updateEmployee(Integer employeeId, Employee employee);

    public List<Employee> getEmployeesByName(String employeeName);
}
