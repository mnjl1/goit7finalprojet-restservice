package goit.finalproject.rest.Service;


import goit.finalproject.rest.model.DateEvent;
import goit.finalproject.rest.model.Department;
import goit.finalproject.rest.model.Employee;
import goit.finalproject.rest.model.WorkReport;

import java.util.List;

public interface DateEventService {

    DateEvent findById(Long id);
//    DateEvent findByDate(String date);
    DateEvent findByDate(String date);
    List<DateEvent> findAllEvents();

    DateEvent save(DateEvent dateEvent);
    void update(DateEvent dateEvent);
    void delete(Long id);

    //additional
    List<DateEvent> findByEmployee(Employee employee);
    List<DateEvent> findByEmployeeAndDate(Employee employee, String date);
    List<DateEvent> findByDepartment(Department department);

//    automatic create new event of day (event = workDay, set = all employees with status = work);
    DateEvent createEventNewDay(String date);
//    void createEventToday();

//    report  for employee of period
    WorkReport reportEmployeeOfPeriod(Employee employee, String dateBeginPeriod, String dateEndPeriod);

}
