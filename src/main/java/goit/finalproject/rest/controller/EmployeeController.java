package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(value = "employee", description = "Operations with Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "List of all employees")//, response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAll();
        if (employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
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

    @ApiOperation(value = "Find employee by first name", response = Employee.class)
    @RequestMapping(value = "/firstName/{firstName}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployeeByFirstName(@PathVariable("firstName") String firstName){
        List<Employee> employeeList = employeeService.findByFirstName(firstName);
        if (employeeList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find employee by last name", response = Employee.class)
    @RequestMapping(value = "/lastName/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployeeByLastName(@PathVariable("lastName") String lastName){
        List<Employee> employeeList = employeeService.findByLastName(lastName);
        if (employeeList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find employee by email", response = Employee.class)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployeeByEmail(@PathVariable("email") String email){
        List<Employee> employeeList = employeeService.findByEmail(email);
        if (employeeList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new employee")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
//        return new ResponseEntity<Position>(position, HttpStatus.CREATED);



    }

    @ApiOperation(value = "Update employee")
    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee){

        Employee existingEmployee = employeeService.getById(employee.getId());
        if (existingEmployee == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);



        }
        else {
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @ApiOperation(value = "Delete employee")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
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
