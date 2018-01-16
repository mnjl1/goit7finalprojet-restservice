package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
class TaskHolder {

    public static final SimpleDateFormat formatForSecondNow = new SimpleDateFormat("hh mm ss");

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

//    @Scheduled(cron="0 0 8 * * *") EVERY DAY AT 8.00 O'CLOCK
//    @Scheduled(fixedDelay=1000) //every seconds
    @Scheduled(fixedDelay=60000) //every 60 seconds
    public void newDay() {
        //do some work
        String time = formatForSecondNow.format(new Date());
        System.out.println("it's work " +time);

////        EVERY DAY
//        String date = "11 01 2018";
        String date = "";
        DateEvent dateEvent = dateEventService.createEventNewDay(date);
        System.out.println("++++============++++++++");
        List<DateStatus> list = dateStatusService.createDay(date);
        System.out.println(dateEvent.getDate());

        List<WorkReport> reports= workReportService.addAllReportForAllEmployeesFirstDayOfMonth(date);
        if (reports.size()>0){
            for(WorkReport w: reports){
                System.out.println(w.getTabelID()+" / "+w.getBeginPeriod()+" / "+w.getEndPeriod()+" / "
                        +w.getCountDay()+" / "+w.getSalaryPeriod());
            }

        }
    }

//    @Scheduled(cron = "0 0 0 1 * *") //ONCE A MONTH
    public void reportMonth(){
        String date = "";
        workReportService.addAllReportForAllEmployeesFirstDayOfMonth(date);
    }

//    @Scheduled(cron = "0 0 0 1 * *") //ONCE A MONTH
    public void mailRobot(){
        String date = "";
        List<WorkReport> reportList = workReportService.findAllReportForAllEmployeesFirstDayOfMonth(date);
        //creating email messanges

        //sending emails


    }



//    @Scheduled(fixedDelay=160000) //every 160 seconds
    public void initDB(){
        log.info("start init position");
        List<Position> positionList = new ArrayList<>();
        Position p1 = new Position("director", 100f);
        positionList.add(p1);
        Position p2 = new Position("sheff", 70f);
        positionList.add(p2);
        Position p3 = new Position("sub-sheff", 70f);
        positionList.add(p3);
        Position p4 = new Position("master", 50f);
        positionList.add(p4);
        Position p5 = new Position("developer Java", 20f);
        positionList.add(p5);
        Position p6 = new Position("developer C#", 20f);
        positionList.add(p6);

        for (Position p: positionList){
            positionService.save(p);
        }

        positionList = positionService.findAllPosition();


        log.info("start init department");
        Set<Department> departmentSet = new HashSet<>();
        Department d1 = new Department("department01");
        departmentSet.add(d1);
        Department d2 = new Department("department02");
        departmentSet.add(d2);
        Department d3 = new Department("department03");
        departmentSet.add(d3);
        Department d4 = new Department("department04");
        departmentSet.add(d4);
        Department d5 = new Department("department05");
        departmentSet.add(d5);
        Department d6 = new Department("department06");
        departmentSet.add(d6);

        for(Department d: departmentSet){
            departmentService.save(d);
        }

        List<Department> departmentList = departmentService.findAllDepartment();



        log.info("start init event");
        List<Event> eventList = new ArrayList<>();
        Event e1 = new Event("workDay");
        eventList.add(e1);
        Event e2 = new Event("techskill");
        eventList.add(e2);

        for(Event e: eventList){
            eventService.save(e);
        }
        eventList = eventService.findAllEvent();

        log.info("start init status");
        StatusWork s1 = new StatusWork("work");
        statusWorkService.save(s1);
        StatusWork s2 = new StatusWork("ill");
        statusWorkService.save(s2);
        StatusWork s3 = new StatusWork("holiday");
        statusWorkService.save(s3);
        StatusWork s4 = new StatusWork("not work");
        statusWorkService.save(s4);

        List<StatusWork> statusWorkList = statusWorkService.findAllStatusWork();

        log.info("start init employee");
        Set<Employee> employeeSet = new HashSet<>();
        Employee em1 = new Employee(1111L,"Hugo","Boss","hugo.boss@gmail.com",
                departmentList.get(0), positionList.get(0),1f,1f,1f,0f);
        employeeSet.add(em1);
        Employee em2 = new Employee(1112L,"Tim","Taller","tim.taller@gmail.com",
                departmentList.get(0), positionList.get(1),1f,1f,1f,0f);
        employeeSet.add(em2);
        Employee em3 = new Employee(1113L,"Tom","Cruse","tom.cruse@gmail.com",
                departmentList.get(0), positionList.get(2),1f,1f,1f,0f);
        employeeSet.add(em3);
        Employee em4 = new Employee(1114L,"Will","Smith","will.smith@gmail.com",
                departmentList.get(0), positionList.get(3),1f,1f,1f,0f);
        employeeSet.add(em4);
        Employee em5 = new Employee(1115L,"Billy","Bons","billy.bons@gmail.com",
                departmentList.get(0), positionList.get(4),1f,1f,1f,0f);
        employeeSet.add(em5);
        Employee em6 = new Employee(1121L,"Tilly","Taller","tilly.taller@gmail.com",
                departmentList.get(1), positionList.get(2),1f,1f,1f,0f);
        employeeSet.add(em6);
        Employee em7 = new Employee(1122L,"Willy","Cruse","willy.cruse@gmail.com",
                departmentList.get(1), positionList.get(3),1f,1f,1f,0f);
        employeeSet.add(em7);
        Employee em8 = new Employee(1123L,"Polly","Smith","polly.smith@gmail.com",
                departmentList.get(1), positionList.get(4),1f,1f,1f,0f);
        employeeSet.add(em8);
        Employee em9 = new Employee(1124L,"Bill","Gates","bill.gates@gmail.com",
                departmentList.get(1), positionList.get(5),1f,1f,1f,0f);
        employeeSet.add(em9);
        Employee em10 = new Employee(1131L,"Robby","Williams","robby.williams@gmail.com",
                departmentList.get(2), positionList.get(2),1f,1f,1f,0f);
        employeeSet.add(em10);
        Employee em11 = new Employee(1132L,"Stive","Jobs","stive.jobs@gmail.com",
                departmentList.get(2), positionList.get(3),1f,1f,1f,0f);
        employeeSet.add(em11);
        Employee em12 = new Employee(1133L,"Polly","Cruse","polly.crouse@gmail.com",
                departmentList.get(2), positionList.get(4),1f,1f,1f,0f);
        employeeSet.add(em12);
        Employee em13 = new Employee(1134L,"Tilly","Gates","tilly.gates@gmail.com",
                departmentList.get(2), positionList.get(5),1f,1f,1f,0f);
        employeeSet.add(em13);
        Employee em14 = new Employee(1135L,"Robby","Jobs","robby.jobs@gmail.com",
                departmentList.get(2), positionList.get(5),1f,1f,1f,0f);
        employeeSet.add(em14);

        for (Employee em: employeeSet){
            employeeService.save(em);
        }

        List<Employee> employeeList = employeeService.findAllEmployees();

        List<String> dates = new ArrayList<>();
        dates.add("20 12 2017");
        dates.add("21 12 2017");
        dates.add("22 12 2017");
        dates.add("23 12 2017");
        dates.add("24 12 2017");
        dates.add("25 12 2017");
        dates.add("26 12 2017");
        dates.add("02 01 2018");
        dates.add("03 01 2018");
        dates.add("04 01 2018");
        dates.add("05 01 2018");
        dates.add("08 01 2018");
        dates.add("09 01 2018");
        dates.add("10 01 2018");

        departmentSet.clear();
        for(Department d:departmentList){
            departmentSet.add(d);
        }
        employeeSet.clear();
        for (Employee employee: employeeList){
            employeeSet.add(employee);
        }
        s1 = statusWorkList.get(0);
        e1 = eventList.get(0);
        log.info("start init date events and status");
        for (String date: dates){
            dateEventService.save(new DateEvent(date, e1, departmentSet, employeeSet));
            for (Employee em: employeeList){
                dateStatusService.save(new DateStatus(em, date, s1));
            }
        }

        log.info("start init report");
        WorkReport workReport = new WorkReport(1112L, "02 01 2018", "15 01 2018");
        workReport.setCountDay(5);
        workReport.setSalaryPeriod(20000f);
        workReportService.add(workReport);
    }
}