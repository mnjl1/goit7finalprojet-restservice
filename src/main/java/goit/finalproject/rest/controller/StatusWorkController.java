package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.model.StatusWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
@Api(value = "statuswork", description = "Operations with Status Work")
public class StatusWorkController {

    @Autowired
    private StatusWorkService statusWorkService;

    @ApiOperation(value = "List of all status work")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StatusWork>> getAllStatusWork(){
        List<StatusWork> statusWorkList = statusWorkService.findAllStatusWork();
        if (statusWorkList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusWorkList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find status by ID", response = StatusWork.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StatusWork> getDepartment(@PathVariable("id") long id){
        StatusWork statusWork = statusWorkService.findById(id);
        if (statusWork == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusWork, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new status")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<StatusWork> addStatusWork(@RequestBody StatusWork statusWork){
        statusWorkService.save(statusWork);
        return new ResponseEntity<StatusWork>(statusWork, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update status")
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateDepartment(@RequestBody StatusWork statusWork){
        StatusWork existingStatusWork = statusWorkService.findById(statusWork.getId());
        if (existingStatusWork == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            statusWorkService.save(statusWork);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete status")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteStatus(@PathVariable("id") long id){
        StatusWork statusWork = statusWorkService.findById(id);
        if (statusWork == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            statusWorkService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
