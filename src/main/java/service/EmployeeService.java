package service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;
import repository.model.Employee;

import java.util.List;

@Service
@ComponentScan(basePackages = {"repository"})
public class EmployeeService {

    public final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee saveEmployee(Employee employee) {
        Employee createEmployee = new Employee(employee.getName(), employee.getRole());
        createEmployee.setDateOfJoining(dateToInt());
        return repository.addEmployee(createEmployee);
    }

    public List<Employee> findAllEmployees() {
        return repository.getAllEmployees();
    }

    public Employee updateEmployee(Employee employee, int id) {
        if(String.valueOf(employee.getDateOfJoining()).length() != 8 ) throw new IllegalArgumentException("Date format is YYYYMMDD");
        if(employee.getDateOfJoining() > dateToInt()) throw new IllegalArgumentException("Date can't be in the future");
        return repository.updateEmployee(employee, id);
    }

    public void deleteEmployee(int employeeId) {
        Employee employeeForDelete = repository.getEmployeeById(employeeId);
        repository.deleteEmployee(employeeForDelete);
    }

    private int dateToInt(){
        return (int)(System.currentTimeMillis() / 1000);
    }
}
