package goit.finalproject.rest.Service;


import goit.finalproject.rest.model.WorkReport;

import java.util.List;

public interface WorkReportService {

    WorkReport add(WorkReport report);
    List<WorkReport> findAll();

//    List<WorkReport> findReportByEmployeeAndBeginPeriodAndEndPeriod(Employee employee, String beginPeriod, String ednPeriod);
    List<WorkReport> findReportByEmployeeAndBeginPeriodAndEndPeriod(Long tabelID, String beginPeriod, String endPeriod);

    List<WorkReport> addAllReportForAllEmployeesFirstDayOfMonth(String month); //"" - previous month
    List<WorkReport> findAllReportForAllEmployeesFirstDayOfMonth(String month);
}
