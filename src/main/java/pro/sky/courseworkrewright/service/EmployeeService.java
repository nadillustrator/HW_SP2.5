package pro.sky.courseworkrewright.service;

import org.springframework.stereotype.Service;
import pro.sky.courseworkrewright.controller.EmployeeController;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.exceptions.EmployeeAlreadyAddException;
import pro.sky.courseworkrewright.exceptions.EmployeeNotFoundException;
import pro.sky.courseworkrewright.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    private static int size = 10;

    private final List<Employee> employees;

    public static int getSize() {
        return size;
    }

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }
    
    public Employee addEmployee(String name, String surname) {
        Employee newEmployee = new Employee(name,surname);
        if (size <= employees.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.contains(newEmployee)){
            throw new EmployeeAlreadyAddException();
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);
        if(employees.contains(newEmployee)){
            employees.remove(newEmployee);
            System.out.println("Employee "+ newEmployee.getName() + newEmployee.getSurname() +" is removed.");
            return newEmployee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);
            if(employees.contains(newEmployee)){
                System.out.println("Employee " + newEmployee.getName() + newEmployee.getSurname() + " found.");
                return newEmployee;
            }

        throw new EmployeeNotFoundException();
    }

    public List<Employee> showEmployees(){
        return new ArrayList<>(employees);
    }

}

