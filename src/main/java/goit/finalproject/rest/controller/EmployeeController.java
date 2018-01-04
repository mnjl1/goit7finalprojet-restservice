package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee){
        Employee existingEmployee = employeeService.getById(employee.getId());
        if (existingEmployee == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            employeeService.save(employee);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id){
        Employee employee = employeeService.getById(id);
        if (employee == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAll();
        if (employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id){
        Employee employee = employeeService.getById(id);
        if (employee == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            employeeService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }
}
