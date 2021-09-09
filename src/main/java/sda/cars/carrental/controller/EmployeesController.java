package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sda.cars.carrental.entity.Branches;
import sda.cars.carrental.entity.Employees;
import sda.cars.carrental.service.BranchService;
import sda.cars.carrental.service.EmployeeService;
@Controller
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public Employees getEmployee (@PathVariable Long id){
        return employeeService.findById(id);
    };
    @PostMapping("/employee/new")
    public Employees newEmployee (@RequestBody Employees newEmployee) {
        employeeService.save(newEmployee);
        return  employeeService.findById(newEmployee.getId());
    }
}
