package pro.sky.courseworkrewright.employees;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee { //dataclass
    @JsonProperty("firstName")
    private final String name;
    @JsonProperty("lastName")
    private final String surname;

    private final int salary;
    private final int departmentId;


    public Employee(String name, String surname, int salary, int departmentId) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getFullName() {
        return this.surname + " " + this.name;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    @Override
    public String toString() {
        return "Employee: " + surname + " " + name + ", department: " + departmentId + ", salary: " + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && departmentId == employee.departmentId && name.equals(employee.name) && surname.equals(employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, salary, departmentId);
    }
}
