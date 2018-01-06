package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.DepartmentService;
import goit.finalproject.rest.model.Department;
import goit.finalproject.rest.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/department")
@Api(value = "department", description = "Operations with Department")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @ApiOperation(value = "Add new department")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        departmentService.save(department);
        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update department")
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateDepartment(@RequestBody Department department){
        Department existingDepartment = departmentService.getById(department.getId());
        if (existingDepartment == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            departmentService.save(department);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find department by ID", response = Department.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Department> getDepartment(@PathVariable("id") long id){
        Department department = departmentService.getById(id);
        if (department == null){
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @ApiOperation(value = "List of all Departments")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = departmentService.getAll();
        if (departments.isEmpty()){
            return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Department")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id){
        Department department = departmentService.getById(id);
        if (department == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            departmentService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }
}
