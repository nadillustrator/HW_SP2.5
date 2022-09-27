package pro.sky.courseworkrewright.service;

import org.springframework.stereotype.Service;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.exceptions.EmployeeAlreadyAddException;
import pro.sky.courseworkrewright.exceptions.EmployeeNotFoundException;
import pro.sky.courseworkrewright.exceptions.EmployeeStorageIsFullException;

import java.util.*;


@Service
public class EmployeeService {
    private static int size = 10;

    private final Map<String, Employee> employees;

    public static int getSize() {
        return size;
    }

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee addEmployee(String name, String surname) {
        Employee newEmployee = new Employee(name,surname);
        if (size <= employees.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsValue(newEmployee)){
            throw new EmployeeAlreadyAddException();
        }
        employees.put(newEmployee.getFullName(), newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);
        if(employees.containsValue(newEmployee)){
            employees.remove(newEmployee.getFullName());
            System.out.println("Employee "+ newEmployee.getName() + newEmployee.getSurname() +" is removed.");
            return newEmployee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);
        if(employees.containsValue(newEmployee)){
            System.out.println("Employee " + newEmployee.getName() + newEmployee.getSurname() + " found.");
            return newEmployee;
        }

        throw new EmployeeNotFoundException();
    }

    public HashMap<String, Employee> showEmployees(){
        return new HashMap<>(employees);
    }

}

