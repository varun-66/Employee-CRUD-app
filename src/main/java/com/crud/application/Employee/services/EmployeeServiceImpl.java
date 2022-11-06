package com.crud.application.Employee.services;

import com.crud.application.Employee.entities.Employee;
import com.crud.application.Employee.controllers.customNOEmployeeFoundException;
import com.crud.application.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee fetchEmployee(Integer id) throws customNOEmployeeFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent())
            throw new customNOEmployeeFoundException("Employee Not Available");
        return employee.get();
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public String updateEmployee(Integer employeeId, Employee employee) {
        Employee employeeDB = null;
        try {
            employeeDB = employeeRepository.findById(employeeId).get();
        } catch (Exception exception){
            System.out.println(exception.toString());
            return "No value present in DB";
        }
        System.out.println("Existing employee with given id => " + employeeDB.toString());

        if(Objects.isNull(employeeDB))
            return "Employee with id: " + employeeId + " doesn't exists in DB";
        System.out.println("Going to change employee details with given id ....");
        if(Objects.nonNull(employee.getAddress())) employeeDB.setAddress(employee.getAddress());
        if(Objects.nonNull(employee.getCode())) employeeDB.setCode(employee.getCode());
        if(Objects.nonNull(employee.getName())) employeeDB.setName(employee.getName());
        employeeRepository.save(employee);
        return "Employee with id: " + employeeId + " updated successfully in DB";
    }

    @Override
    public List<Employee> getEmployeesByName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }

}
