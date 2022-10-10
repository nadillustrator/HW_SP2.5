package pro.sky.courseworkrewright.service;

import org.springframework.stereotype.Service;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.exceptions.EmployeeAlreadyAddException;
import pro.sky.courseworkrewright.exceptions.EmployeeNotFoundException;
import pro.sky.courseworkrewright.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static int size = 40;
    private final Map<String, Employee> employees = new HashMap<>();
    private final ValidatorService validator = new ValidatorService();


    public Employee addEmployee(String name, String surname, int salary, int departmentId) {
        Employee newEmployee = new Employee(
                validator.validate(name),
                validator.validate(surname),
                salary,
                departmentId
        );
        if (size <= employees.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddException();
        }
        employees.put(newEmployee.getFullName(), newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname, int salary, int departmentId) {
        Employee newEmployee = new Employee(name, surname, salary, departmentId);
        if (employees.containsKey(newEmployee.getFullName())) {
            System.out.println("Employee " + newEmployee.getFullName() + " is removed.");
            return employees.remove(newEmployee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname, int salary, int departmentId) {
        Employee newEmployee = new Employee(name, surname, salary, departmentId);
        if (employees.containsKey(newEmployee.getFullName())) {
            System.out.println("Employee " + newEmployee.getFullName() + " found.");
            return newEmployee;
        }

        throw new EmployeeNotFoundException();
    }

    public List <Employee> getAll(){
        return new ArrayList<>(employees.values());
    }

    public static int getSize() {
        return size;
    }

}
