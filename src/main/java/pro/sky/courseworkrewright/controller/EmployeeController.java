package pro.sky.courseworkrewright.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String showWelcome() {
        return "Welcome!";
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName){
       return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/size")
    public int getSize(){
        return EmployeeService.getSize();
    }

    @GetMapping("/showEmployees")
    public List<Employee> showEmployees(){
       List<Employee> employees = employeeService.showEmployees();
       return employees;
    }

}
