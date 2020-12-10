package services;

import entities.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EmployeeService {
    private static EmployeeService instance;
    private Map<Integer, Employee> employees = new HashMap<>();

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    public Employee addEmployee(Employee employee) {
        int id = employees.size()+1;
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public Set<Employee> getAllEmployees() {
        return new HashSet<>(employees.values());
    }

    public Employee updateEmployee(Employee employee) {
        int id = employee.getId();
        if (employees.containsKey(id)) {
            employees.put(id, employee);
            return employee;
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        return employees.remove(id) != null;
    }
}
