package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.PositionService;
import goit.finalproject.rest.model.Position;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
@Api(value = "position", description = "Operations with Position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "List of all positions")//, response = Iterable.class)
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Position>> getAllPosition(){
        List<Position> positionList = positionService.findAllPosition();
        if (positionList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find position by ID", response = Position.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Position> getPosition(@PathVariable("id") long id){
        Position position = positionService.findById(id);
        if (position == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @ApiOperation(value = "Find position by name", response = Position.class)
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Position>> getPositionByName(@PathVariable("name") String name){
        List<Position> positionList = positionService.findByName(name);
        if (positionList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }


    @ApiOperation(value = "Add new position")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Position> addPosition(@RequestBody Position position){
        positionService.save(position);
        return new ResponseEntity<>(position, HttpStatus.CREATED);
//        return new ResponseEntity<Position>(position, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update position")
    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updatePosition(@RequestBody Position position){
        Position existingPosition = positionService.findById(position.getId());
        if (existingPosition == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            positionService.save(position);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete position")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deletePosition(@PathVariable("id") long id){
        Position position = positionService.findById(id);
        if (position == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            positionService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }




}
