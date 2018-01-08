package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.RoleService;
import goit.finalproject.rest.model.Employee;
import goit.finalproject.rest.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "role", description = "Creating new Role")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public RoleService roleService;

    @ApiOperation(value = "Add new role")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        roleService.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update role")
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateRole(@RequestBody Role role){
        Role existingRole = roleService.getById(role.getId());
        if (existingRole == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            roleService.save(role);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find role by ID", response = Employee.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRole(@PathVariable("id") long id){
        Role role = roleService.getById(id);
        if (role == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @ApiOperation(value = "List of all roles")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = roleService.getAll();
        if (roles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete role")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") long id){
        Role role = roleService.getById(id);
        if (role == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            roleService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

}
