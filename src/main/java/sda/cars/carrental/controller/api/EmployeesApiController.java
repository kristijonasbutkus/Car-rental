package sda.cars.carrental.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Employees;
import sda.cars.carrental.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee/api/")
@Slf4j
public class EmployeesApiController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee-list")
    public List<Employees> showEmployeeList(Employees employees) {
        List<Employees> employeeList = employeeService.findAll();
        return employeeList;
    }

    @GetMapping("/employee/{id}")
    public Employees getEmployee(@PathVariable Long id) {
        return employeeService.findById(id);
    }


    @PutMapping("/employee/{id}")
    public Employees updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody Employees employeeDetails) {
        employeeService.save(employeeDetails);
        return employeeService.findById(id);
    }

    @DeleteMapping ("/employee/{id}")
    public Boolean deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteById(id);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

    }

    @PostMapping("/employee/new")
    public Employees newEmployee (@RequestBody Employees newEmployee) {
        employeeService.save(newEmployee);
        return  employeeService.findById(newEmployee.getId());
    }
}
