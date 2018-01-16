package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.DepartmentService;
import goit.finalproject.rest.model.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@Api(value = "department", description = "Operations with Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "List of all departments")//, response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Department>> getAllDepartment(){
        List<Department> departmentList = departmentService.findAllDepartment();
        if (departmentList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find department by ID", response = Department.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Department> getDepartment(@PathVariable("id") long id){
        Department department = departmentService.findById(id);
        if (department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @ApiOperation(value = "Find department by name", response = Department.class)
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> getDepartmentByName(@PathVariable("name") String name){
        List<Department> departmentList = departmentService.findByName(name);
        if (departmentList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new department")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        departmentService.save(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
//        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update department")
    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        Department existingDepartment = departmentService.findById(department.getId());
        if (existingDepartment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            departmentService.save(department);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete department")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id){
        Department department = departmentService.findById(id);
        if (department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            departmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

}
