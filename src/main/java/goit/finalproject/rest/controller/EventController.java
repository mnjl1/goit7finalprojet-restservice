package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.EventService;
import goit.finalproject.rest.model.Employee;
import goit.finalproject.rest.model.Event;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Event", description = "Creating new Event")
@RequestMapping("/event")
@RestController
public class EventController {
    @Autowired
    public EventService eventService;

    @ApiOperation(value = "Add new event")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Event> addEvent(@RequestBody Event event){
        eventService.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update event")
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateEvent(@RequestBody Event event){
        Event existingEvent = eventService.getById(event.getId());
        if (existingEvent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find event by ID", response = Event.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@PathVariable("id") long id){
        Event event = eventService.getById(id);
        if (event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @ApiOperation(value = "List of all events", response = Iterable.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.getAll();
        if (events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete event")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") long id){
        Event event = eventService.getById(id);
        if (event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            eventService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
