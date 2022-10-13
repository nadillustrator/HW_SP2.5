package pro.sky.courseworkrewright.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.courseworkrewright.employees.Employee;
import pro.sky.courseworkrewright.exceptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pro.sky.courseworkrewright.service.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;


    @BeforeEach
    public void beforeEach() {
        when(employeeService.getAll()).thenReturn(
                List.of(
                        employee1, employee2, employee3, employee4,
                        employee5DoubleName, employee5DoubleSurname
                )
        );
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalary")
    public void shouldReturnEmployeeWithMaxSalary(int department,
                                                  Employee expected) {
        assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalary")
    public void shouldReturnEmployeeWithMinSalary(int department,
                                                  Employee expected) {
        assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department)).isEqualTo(expected);
    }

    @Test
    public void methodFindEmployeeWithMinSalaryShouldReturnEmployeeNotFoundException() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMinSalaryFromDepartment(5));
    }

    @Test
    public void methodFindEmployeeWithMaxSalaryShouldReturnEmployeeNotFoundException() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMaxSalaryFromDepartment(5));
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartment")
    public void shouldReturnAllEmployeesFromDepartment(int department,
                                                       List<Employee> expected) {
        assertThat(departmentService.findEmployeesFromDepartment(department)).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void shouldReturnAllEmployeesFromDepartment() {
        assertThat(departmentService.sortEmployeesByDepartment())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(employee1, employee2, employee3),
                                2, List.of(employee4, employee5DoubleSurname),
                                3, List.of(employee5DoubleName)
                        ));
    }

    public static Stream<Arguments> employeeWithMaxSalary() {
        return Stream.of(
                Arguments.of(1, employee2),
                Arguments.of(2, employee5DoubleSurname),
                Arguments.of(3, employee5DoubleName)
        );
    }

    public static Stream<Arguments> employeeWithMinSalary() {
        return Stream.of(
                Arguments.of(1, employee1),
                Arguments.of(2, employee4),
                Arguments.of(3, employee5DoubleName)
        );
    }

    public static Stream<Arguments> employeesFromDepartment() {
        return Stream.of(
                Arguments.of(1, List.of(employee1, employee2, employee3)),
                Arguments.of(2, List.of(employee4, employee5DoubleSurname)),
                Arguments.of(3, List.of(employee5DoubleName))
        );
    }

}



