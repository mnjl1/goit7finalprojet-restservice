package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.DateStatusService;
import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.model.DateStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabel")
@Api(value = "datestatus", description = "Operations with Status with Date")
public class DateStatusController {

    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "List of all dateStatus")//, response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<DateStatus>> getAllDateStatus(){
        List<DateStatus> dateStatusList = dateStatusService.findAllSatuses();
        if (dateStatusList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find dateStatus by ID", response = DateStatus.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<DateStatus> getDateStatus(@PathVariable("id") long id){
        DateStatus dateStatus = dateStatusService.findById(id);
        if (dateStatus == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatus, HttpStatus.OK);
    }

    @ApiOperation(value = "Find dateStatus by date", response = DateStatus.class)
    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<DateStatus>> getDateEventByDate(@PathVariable("date") String date){
        List<DateStatus> dateStatusList = dateStatusService.findByDate(date);
        if (dateStatusList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find dateStatus by ID of employee", response = DateStatus.class)
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DateStatus>> getDateEventByEmployee(@PathVariable("id") Long id){
        List<DateStatus> dateStatusList = dateStatusService.findByEmployee(employeeService.findById(id));
        if (dateStatusList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find dateStatus by ID of employee and date", response = DateStatus.class)
    @RequestMapping(value = "/employeeDate/{id}/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<DateStatus>> getDateEventByEmployee(@PathVariable("id") Long id, @PathVariable("date") String date){
        List<DateStatus> dateStatusList = dateStatusService.findByEmployeeAndDate(employeeService.findById(id), date);
        if (dateStatusList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }





    @ApiOperation(value = "Add new dateStatus")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<DateStatus> addDateStatus(@RequestBody DateStatus dateStatus){
        dateStatusService.save(dateStatus);
        return new ResponseEntity<>(dateStatus, HttpStatus.CREATED);
//        return new ResponseEntity<Position>(position, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update dateStatus")
    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateDatestatus(@RequestBody DateStatus dateStatus){
        DateStatus existingDateStatus = dateStatusService.findById(dateStatus.getId());
        if (existingDateStatus == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            dateStatusService.save(dateStatus);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete dateStatus")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteDateStatus(@PathVariable("id") long id){
        DateStatus dateStatus = dateStatusService.findById(id);
        if (dateStatus == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            dateStatusService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
