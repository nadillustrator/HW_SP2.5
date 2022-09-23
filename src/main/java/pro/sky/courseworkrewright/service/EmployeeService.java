package pro.sky.courseworkrewright.service;

import org.springframework.stereotype.Service;
import pro.sky.courseworkrewright.controller.EmployeeController;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.exceptions.EmployeeAlreadyAddException;
import pro.sky.courseworkrewright.exceptions.EmployeeNotFoundException;
import pro.sky.courseworkrewright.exceptions.EmployeeStorageIsFullException;

import java.util.Arrays;

@Service
public class EmployeeService {
    private static int size;

    private final Employee[] employees;

    public static int getSize() {
        return size;
    }

    public EmployeeService() {
        this.employees = new Employee[10];
    }
    
    public Employee addEmployee(String name, String surname) {
        if (size >= employees.length) {
            throw new EmployeeStorageIsFullException();
        }
        Employee newEmployee = new Employee(name,surname);
        for (int i = 0; i < employees.length; i++) {
            if(newEmployee.equals(employees[i])) {
                throw new EmployeeAlreadyAddException();
            }
        }
        employees[size++] = newEmployee;
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);
        for (int i = 0; i < size; i++) {
            if(newEmployee.equals(employees[i])){
                System.out.println("Employee " + employees[i].getName() + employees[i].getSurname() + " is removed.");
                System.arraycopy(employees, i + 1, employees, i, size - i - 1);
                employees[size - 1] = null;
                size--;
                return newEmployee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);
        for (int i = 0; i < size; i++) {
            if(newEmployee.equals(employees[i])){
                System.out.println("Employee " + employees[i].getName() + employees[i].getSurname() + " found.");
                return newEmployee;
            }
        }
        throw new EmployeeNotFoundException();
    }

}

