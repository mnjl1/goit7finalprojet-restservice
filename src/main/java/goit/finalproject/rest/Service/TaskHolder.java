package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
class TaskHolder {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TaskHolder.class);

    @Autowired
    private PositionService positionService;

    @Autowired
    private DateEventService dateEventService;

    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private WorkReportService workReportService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StatusWorkService statusWorkService;

    @Scheduled(cron="0 0 8 * * *") //EVERY DAY AT 8.00 O'CLOCK
    public void newDay() {
        String date = "";
        log.info("Create new event by date {}", date);
        dateEventService.createEventNewDay(date);
        log.info("Create statuses of all employees by date {}", date);
        dateStatusService.createDay(date);
    }

    @Scheduled(cron = "0 0 0 1 * *") //ONCE A MONTH
    public void reportMonth(){
        String date = "";
        log.info("Create reports of all employees by period {}", date);
        workReportService.addAllReportForAllEmployeesFirstDayOfMonth(date);
        List<WorkReport> reportList = workReportService.findAllReportForAllEmployeesFirstDayOfMonth(date);

        log.info("Send reports of all employees by period {}", date);
        for(WorkReport w: reportList) {
           workReportService.sendEmailOfPeriod(w);
        }
    }
}