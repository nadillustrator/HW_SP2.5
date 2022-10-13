package pro.sky.courseworkrewright.service;

import org.springframework.stereotype.Service;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.exceptions.EmployeeNotFoundException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeWithMaxSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Collection<Employee> findEmployeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> sortEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
