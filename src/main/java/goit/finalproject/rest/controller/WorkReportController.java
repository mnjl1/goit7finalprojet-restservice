package goit.finalproject.rest.controller;

import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.Service.WorkReportService;
import goit.finalproject.rest.model.WorkReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@Api(value = "workreport", description = "Operations with Report")
public class WorkReportController {

    @Autowired
    private WorkReportService workReportService;

    @ApiOperation(value = "List of all reports")
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<WorkReport>> getAllReport(){
        List<WorkReport> workReports = workReportService.findAll();
        if (workReports.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workReports, HttpStatus.OK);
    }

    @ApiOperation(value = "Find report by tabelID and period", response = WorkReport.class)
    @RequestMapping(value = "/{tabelID}/{begin}/{end}", method = RequestMethod.GET)
    public ResponseEntity<WorkReport> getReport(@PathVariable("tabelID") long tabelID,
                                                    @PathVariable("begin") String beginPeriod, @PathVariable("end") String endPeriod){
        List<WorkReport> list = workReportService.findReportByEmployeeAndBeginPeriodAndEndPeriod(tabelID, beginPeriod, endPeriod);
        WorkReport workReport = null;
        if (list.size()>0){
            workReport = list.get(list.size()-1);
        }
        if (workReport == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workReport, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new report")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<WorkReport> addReport(@RequestBody WorkReport workReport){
        workReportService.add(workReport);
        return new ResponseEntity<WorkReport>(workReport, HttpStatus.CREATED);
    }


//    @ApiOperation(value = "List of all report")
//    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json")
//    public ResponseEntity<List<StatusWork>> getAllStatusWork(){
//        List<StatusWork> statusWorkList = statusWorkService.findAllStatusWork();
//        if (statusWorkList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(statusWorkList, HttpStatus.OK);
//    }

}
