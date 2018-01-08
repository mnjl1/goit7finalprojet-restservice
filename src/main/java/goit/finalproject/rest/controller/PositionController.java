package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.PositionService;
import goit.finalproject.rest.model.Position;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
@Api(value = "Position", description = "Operation with position")
public class PositionController {
    @Autowired
    public PositionService positionService;


    @ApiOperation(value = "Add new position")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Position> addPosition(@RequestBody Position position){
        positionService.save(position);
        return new ResponseEntity<>(position, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Position")
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> updatePosition(@RequestBody Position position){
        Position existingPosition = positionService.getById(position.getId());
        if (existingPosition == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            positionService.save(position);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find position by ID", response = Position.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Position> getPosition(@PathVariable("id") long id){
        Position position = positionService.getById(id);
        if (position == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @ApiOperation(value = "List of all position", response = Iterable.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Position>> getAllPositions(){
        List<Position> positions = positionService.getAll();
        if (positions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deletePosition(@PathVariable("id") long id){
        Position position = positionService.getById(id);
        if (position == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            positionService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }


}
