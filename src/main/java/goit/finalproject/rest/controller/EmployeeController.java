package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(value = "employee", description = "Operations with Employee")
public class EmployeeController {

    @Autowired
    protected EmployeeService employeeService;

    @ApiOperation(value = "Add new employee")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update employee")
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee){
        Employee existingEmployee = employeeService.getById(employee.getId());
        if (existingEmployee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find employee by ID", response = Employee.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id){
        Employee employee = employeeService.getById(id);
        if (employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "List of all employees", response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAll();
        if (employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id){
        Employee employee = employeeService.getById(id);
        if (employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
