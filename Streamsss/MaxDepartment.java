package Streamsss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MaxDepartment {
    public static class Employee{
        public int id;
        public String name;
        public int salary;
        public String department;
        public Employee(int id, String name, int salary, String department) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + ", department='" + department + "'}";
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 50000, "HR"));
        employees.add(new Employee(2, "Bob", 60000, "IT"));
        employees.add(new Employee(3, "Charlie", 55000, "HR"));
        employees.add(new Employee(4, "David", 70000, "IT"));
        employees.add(new Employee(5, "John", 80000, "IT"));
        employees.add(new Employee(6, "Robert", 90000, "IT"));
        employees.add(new Employee(7, "Sally", 90000, "IT"));
        employees.add(new Employee(8, "Jack", 90000, "HR"));


        // Get Employee Details, with sorted Employee name
//        List<Employee> sortedNames = employees.stream().
//                sorted(Comparator.comparing(x->x.name))
//                .collect(Collectors.toList());
        System.out.println("Employee Details with sorted Employee name :");
        employees.stream()
                .sorted(Comparator.comparing(x->x.name))
                .forEach(Employee -> System.out.println(Employee.toString()));
        System.out.println(STR."------------------------------------------------------------------------");

        //Employeee names
        System.out.println("Employee Names only :");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println(STR."------------------------------------------------------------------------");

        // Get Employee Details, with sorted Employee name,salary, department
        System.out.println("Employee Details with sorted Employee name,salary, department :");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName)
                        .thenComparing(Employee::getSalary)
                        .thenComparing(Employee::getDepartment))
                .forEach(Employee -> System.out.println(Employee.toString()));
        System.out.println(STR."------------------------------------------------------------------------");


        // Get Max salary
        System.out.println("Employee Max Salary :");
        employees.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .ifPresent(System.out::println);
        System.out.println(STR."------------------------------------------------------------------------");



        // Get Sum of salary as per department, find the department with highhest salary
        System.out.println("Department with highest salary :");
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingInt(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
        System.out.println(STR."------------------------------------------------------------------------");

        // Group employees by department ,Expected structure: Map<String, List<Employee>>
        System.out.println("Group employees by department");
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment));
        System.out.println(STR."------------------------------------------------------------------------");

        // Count employees in each department
        System.out.println("Count employees in each department : ");
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()))
                .forEach((department, count) -> System.out.println(department + ": " + count));
        System.out.println(STR."------------------------------------------------------------------------");

        // Max salary in each department
        System.out.println("Find the highest paid employee in each department");
        employees.stream()
                .collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.maxBy(Comparator.comparingInt(Employee :: getSalary))))
                .forEach((department, maxSalary) -> System.out.println(department + ": "    + maxSalary.orElse(null)));
        System.out.println(STR."------------------------------------------------------------------------");


        // Sum of salaries using reduce method(sums primitive and objects)
        int total = employees.stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
        System.out.println(total);
        System.out.println(STR."------------------------------------------------------------------------");

        // Sum of all salaries
        System.out.println("Sum of all Employee  Salary :");
        int sum = employees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
        System.out.println(sum);
        System.out.println(STR."------------------------------------------------------------------------");

        // Average salary of employees in each department
        System.out.println("Average salary of employees in each department :");
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee :: getDepartment, Collectors.averagingInt(Employee :: getSalary)))
                .forEach((department, averageSalary) -> System.out.println(department + ": " + averageSalary));
        System.out.println(STR."------------------------------------------------------------------------");

        // maxBy()
        System.out.print("Max salary  : ");
        employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)))
                .ifPresent(System.out::println);
        System.out.println(STR."------------------------------------------------------------------------");

        // or
        employees.stream()
                .mapToInt(Employee :: getSalary)
                .max()
                .ifPresent(System.out::println);
        System.out.println(STR."------------------------------------------------------------------------");

        // Remove duplicate emp
        employees.stream()
                .collect(Collectors.toMap(Employee :: getId, e -> e, (e1,e2) -> e1))
                .values()
                .forEach(System.out::println);
        System.out.println(STR."------------------------------------------------------------------------");

    }
}
