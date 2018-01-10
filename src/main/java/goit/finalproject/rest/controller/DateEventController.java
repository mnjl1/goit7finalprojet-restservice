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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dateEvent")
@Api(value = "dateevent", description = "Operations with Event whith Date")
public class DateEventController {

    @Autowired
    private DateEventService dateEventService;

    @ApiOperation(value = "List of all dateEvent")
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<DateEvent>> getAllDateEvent(){
        List<DateEvent> dateEventList = dateEventService.findAllEvents();
        if (dateEventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dateEventList, HttpStatus.OK);
    }

}
