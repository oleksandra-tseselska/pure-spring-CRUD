package web.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repository.model.Employee;
import service.EmployeeService;

import java.util.List;
@Controller
@RequestMapping("/employee")
@ComponentScan(basePackages = {"service"})
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @ResponseBody
//    @PostMapping("/add")
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> saveEmployee(
            @RequestBody @Valid Employee employee
    ) {
        Employee employeeSaved = service.saveEmployee(employee);
        return ResponseEntity.ok(employeeSaved);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> employees = service.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/edit/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable @NotNull int employeeId,
            @RequestBody @Valid  Employee employee,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Employee updateEmployee = service.updateEmployee(employee, employeeId);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable @NotNull int employeeId
    ) {
        service.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
