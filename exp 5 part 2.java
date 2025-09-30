Part a: Sorting Employee Objects Using Lambda Expressions

  import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Roshni", 22, 55000));
        employees.add(new Employee("Abhi", 25, 60000));
        employees.add(new Employee("Isha", 21, 45000));
        employees.add(new Employee("Sonu", 23, 50000));

        System.out.println("Original List:");
        employees.forEach(System.out::println);

        // Sort by name
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by age
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by salary (descending)
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }
}

Part b: Filtering and Sorting Students Using Streams

  import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " | Marks: " + marks;
    }
}

public class StudentFiltering {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Roshni", 85),
            new Student("Abhi", 65),
            new Student("Isha", 92),
            new Student("Sonu", 70),
            new Student("Riya", 78)
        );

        System.out.println("Students Scoring Above 75% (Sorted by Marks):");
        students.stream()
                .filter(s -> s.marks > 75) // filter
                .sorted((s1, s2) -> Double.compare(s1.marks, s2.marks)) // sort ascending
                .map(s -> s.name) // extract names
                .forEach(System.out::println); // print names
    }
}
part c
  import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;

class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " | Price: " + price + " | Category: " + category;
    }
}

public class ProductStreamOperations {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 60000, "Electronics"),
            new Product("Phone", 30000, "Electronics"),
            new Product("Tablet", 25000, "Electronics"),
            new Product("Shirt", 1200, "Clothing"),
            new Product("Jeans", 2500, "Clothing"),
            new Product("Fridge", 40000, "Appliances"),
            new Product("Washing Machine", 35000, "Appliances")
        );

        // Group by category
        Map<String, List<Product>> groupedByCategory =
                products.stream().collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped by Category:");
        groupedByCategory.forEach((cat, list) -> {
            System.out.println(cat + " -> " + list);
        });

        // Find most expensive product in each category
        Map<String, Optional<Product>> maxByCategory =
                products.stream().collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));
        System.out.println("\nMost Expensive Product in Each Category:");
        maxByCategory.forEach((cat, prod) -> System.out.println(cat + " -> " + prod.get()));

        // Calculate average price of all products
        double avgPrice = products.stream()
                                  .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}

