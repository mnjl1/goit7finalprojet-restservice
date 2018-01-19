package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.InitDBService;
import goit.finalproject.rest.Service.StatusWorkService;
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
@RequestMapping("/init")
@Api(value = "event", description = "Initialization full DB")
public class InitDBController {

    @Autowired
    private InitDBService initDBService;

    @Autowired
    private StatusWorkService statusWorkService;

    @ApiOperation(value = "Full DB")//, response = Iterable.class)
    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StatusWork>> fullAllTables(){
        initDBService.full();
        List<StatusWork> statusWorkList= statusWorkService.findAllStatusWork();
        if (statusWorkList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusWorkList, HttpStatus.OK);
    }
}
