package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.DateEventService;
import goit.finalproject.rest.Service.DateStatusService;
import goit.finalproject.rest.model.DateEvent;
import goit.finalproject.rest.model.StatusWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dateEvent")
@Api(value = "dateevent", description = "Operations with Event with Date")
public class DateEventController {

    @Autowired
    private DateEventService dateEventService;

    @ApiOperation(value = "List of all dateEvents")//, response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<DateEvent>> getAllDateEvent(){
        List<DateEvent> dateEventList = dateEventService.findAllEvents();
        if (dateEventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dateEventList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find dateEvent by ID", response = DateEvent.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<DateEvent> getDateEvent(@PathVariable("id") long id){
        DateEvent dateEvent = dateEventService.findById(id);
        if (dateEvent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateEvent, HttpStatus.OK);
    }

    @ApiOperation(value = "Find dateEvent by date", response = DateEvent.class)
    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<DateEvent> getDateEventByFirstName(@PathVariable("date") String date){
        DateEvent dateEventList = dateEventService.findByDate(date);
        if (dateEventList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateEventList, HttpStatus.OK);
    }




    @ApiOperation(value = "Add new dateEvent")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<DateEvent> addDateEvent(@RequestBody DateEvent dateEvent){
        dateEventService.save(dateEvent);
//        return new ResponseEntity<>(dateEvent, HttpStatus.CREATED);
        return new ResponseEntity<DateEvent>(dateEvent, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update dateEvent")
    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateDateEvent(@RequestBody DateEvent dateEvent){
        DateEvent existingDateEvent = dateEventService.findById(dateEvent.getId());
        if (existingDateEvent == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            dateEventService.save(dateEvent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete dateEvent")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteDateEvent(@PathVariable("id") long id){
        DateEvent dateEvent = dateEventService.findById(id);
        if (dateEvent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            dateEventService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
