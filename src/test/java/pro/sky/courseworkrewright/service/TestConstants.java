package pro.sky.courseworkrewright.service;

import pro.sky.courseworkrewright.employees.Employee;

public class TestConstants {


    public static final Employee employee1 = new Employee("Petr", "Petrov", 45000, 1);
    public static final Employee employee2 = new Employee("Ivan", "Ivanov", 98000, 1);
    public static final Employee employee3 = new Employee("Alexandr", "Fetisov", 52800, 1);
    public static final Employee employee4 = new Employee("Nikolay", "Makarov", 26900, 2);
    public static final Employee employee5DoubleSurname = new Employee("Ivan", "Ivanov-Petrov", 98000, 2);
    public static final Employee employee5DoubleName = new Employee("Ivan-Petr", "Ivanov", 98000, 3);


    public static final Employee employee1WithCapitalLetters = new Employee("PETR", "PETROV", 45000, 1);
    public static final Employee employee1WithLowCaseLetters = new Employee("petr", "petrov", 45000, 1);
    public static final Employee employee1IncorrectSymbols = new Employee("p$tr", "petrov", 45000, 1);

}
