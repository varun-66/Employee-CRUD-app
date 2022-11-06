package com.crud.application.Employee.controllers;

import com.crud.application.Employee.entities.Employee;
import com.crud.application.Employee.services.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Value("${welcome.message}")
    private String welcomeMessage;

    @GetMapping("/")
    public String getMapping() {
        return welcomeMessage;
    }

    /**
     * All request body in JSON gets converted to Employee object(primitively we use object mapper) using @RequestBody
     * @param employee
     * @return Employee
     */
    /**
     * CREATE
     * @Valid check against Employee Entity to parse only valid JSON, constraints mentioned in Entity
     */
    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        LOGGER.info("Inside save Employee for " , employee);
        return employeeService.saveEmployee(employee);
    }
    /**
     * READ
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        LOGGER.info("Inside Get Controller for all employees");
        return employeeService.getEmployees();
    }

    /**
     * Get Data for ID
     * If no ID present in DB, handle using customer exception
     * @param id
     * @return Employee
     */
    @GetMapping("/employee/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Integer id) throws customNOEmployeeFoundException {
        LOGGER.info("Fetching data for given id: " + id);
        return employeeService.fetchEmployee(id);
    }
    /*
    Delete
     */
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return "Employee with id " + employeeId + " Deleted Successfully";
    }
    /*
    Update
    bug: if we don't provide id in json then this won't save in db
     */
    @PutMapping("/employee/{id}")
    public String updateEmployee(@PathVariable("id") Integer employeeId, @RequestBody Employee employee){
        return employeeService.updateEmployee(employeeId, employee);
    }

    /**
     * Fetch employees from db for given employee name
     */
    @GetMapping("/employees/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable("name") String employeeName){
        return employeeService.getEmployeesByName(employeeName);
    }

}
