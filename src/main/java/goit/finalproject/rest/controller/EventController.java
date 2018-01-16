package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.EventService;
import goit.finalproject.rest.model.Event;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@Api(value = "event", description = "Operations with Event")
public class EventController {

    @Autowired
    private EventService eventService;

    @ApiOperation(value = "List of all events")//, response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Event>> getAllEvent(){
        List<Event> eventList = eventService.findAllEvent();
        if (eventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find status by ID", response = Event.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@PathVariable("id") long id){
        Event event = eventService.findById(id);
        if (event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @ApiOperation(value = "Find status by name", response = Event.class)
    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventByType(@PathVariable("type") String type){
        List<Event> eventList = eventService.findByType(type);
        if (eventList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }


    @ApiOperation(value = "Add new position")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Event> addEvent(@RequestBody Event event){
        eventService.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
//        return new ResponseEntity<Position>(position, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update position")
    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updateEvent(@RequestBody Event event){
        Event existingEvent = eventService.findById(event.getId());
        if (existingEvent == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete position")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") long id){
        Event event = eventService.findById(id);
        if (event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            eventService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
