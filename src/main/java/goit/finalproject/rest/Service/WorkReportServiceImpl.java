package goit.finalproject.rest.Service;

import goit.finalproject.rest.email.EmailService;
import goit.finalproject.rest.email.Mail;
import goit.finalproject.rest.model.Employee;
import goit.finalproject.rest.model.WorkReport;
import goit.finalproject.rest.model.DateEvent;
import goit.finalproject.rest.repository.EmployeeRepository;
import goit.finalproject.rest.repository.WorkReportRepository;
import goit.finalproject.rest.repository.DateEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkReportServiceImpl implements WorkReportService{
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WorkReportService.class);
    public static final SimpleDateFormat formatForMonthNow = new SimpleDateFormat("MM yyyy");

    @Autowired
    private WorkReportRepository workReportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DateEventService dateEventService;

    @Autowired
    private EmailService emailService;

    @Override
    public WorkReport add(WorkReport report) {
        if (report!=null){
            log.info("Add new report for emlpoyee {}", report.getTabelID());
            return workReportRepository.save(report);
        }
        return null;
    }

    @Override
    public List<WorkReport> findAll() {
        log.info("Find all reports");
        return workReportRepository.findAll();
    }

    @Override
    public List<WorkReport> findByTabelId(Long tabelID) {
        log.info("Find report by tabel ID {}", tabelID);
        List<WorkReport> list = workReportRepository.findByTabelID(tabelID);
        if (list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<WorkReport> findReportByEmployeeAndBeginPeriodAndEndPeriod(Long tabelID, String beginPeriod, String endPeriod) {
        log.info("Find report for emlpoyee {} begin {} - end {}", tabelID, beginPeriod, endPeriod);
        List<WorkReport> reports = workReportRepository.findByTabelIDAndBeginPeriodAndEndPeriod(tabelID, beginPeriod, endPeriod);
        return reports;
    }

    @Override
    public List<WorkReport> addAllReportForAllEmployeesFirstDayOfMonth(String month) { //"" - previous month
        List<WorkReport> reports = new ArrayList<>();
        String[] monthReportList = null;
//        Date
        if ("".equals(month)){
            month = formatForMonthNow.format(new Date());
            monthReportList = month.split(" ");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(Integer.parseInt(monthReportList[1]),
                    Integer.parseInt(monthReportList[0])-1,
                    Integer.parseInt("00"));
            month = calendar.get(Calendar.MONTH)+1+" "+calendar.get(Calendar.YEAR);
        }
        monthReportList = month.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(monthReportList[1]),
                Integer.parseInt(monthReportList[0]),
                Integer.parseInt("0"));
        String beginPeriod = "01"
                +(((calendar.get(Calendar.MONTH)+1)<10)?(" 0"):(" "))
                +(calendar.get(Calendar.MONTH)+1)
                +" "
                +calendar.get(Calendar.YEAR);
        String endPeriod = ((calendar.get(Calendar.DAY_OF_MONTH)<10)?("0"):(""))
                +calendar.get(Calendar.DAY_OF_MONTH)
                +(((calendar.get(Calendar.MONTH)+1)<10)?(" 0"):(" "))
                +(calendar.get(Calendar.MONTH)+1)
                +" "
                +calendar.get(Calendar.YEAR);

        List<Employee> employees = employeeRepository.findAll();

        if (!employees.isEmpty()){
            for(Employee e: employees){

               WorkReport report = workReportRepository.save(dateEventService.reportEmployeeOfPeriod(e, beginPeriod,endPeriod));
                reports.add(report);
            }
        }
        return reports;
    }

    @Override
    public List<WorkReport> findAllReportForAllEmployeesFirstDayOfMonth(String month) { //"" - previous month
        List<WorkReport> reports = new ArrayList<>();
        String[] monthReportList = null;
//        Date
        if ("".equals(month)){
            month = formatForMonthNow.format(new Date());
            monthReportList = month.split(" ");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(Integer.parseInt(monthReportList[1]),
                    Integer.parseInt(monthReportList[0])-1,
                    Integer.parseInt("00"));
            month = calendar.get(Calendar.MONTH)+1+" "+calendar.get(Calendar.YEAR);
        }
        monthReportList = month.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(monthReportList[1]),
                Integer.parseInt(monthReportList[0]),
                Integer.parseInt("0"));
        String beginPeriod = "01"
                +(((calendar.get(Calendar.MONTH)+1)<10)?(" 0"):(" "))
                +(calendar.get(Calendar.MONTH)+1)
                +" "
                +calendar.get(Calendar.YEAR);
        String endPeriod = ((calendar.get(Calendar.DAY_OF_MONTH)<10)?("0"):(""))
                +calendar.get(Calendar.DAY_OF_MONTH)
                +(((calendar.get(Calendar.MONTH)+1)<10)?(" 0"):(" "))
                +(calendar.get(Calendar.MONTH)+1)
                +" "
                +calendar.get(Calendar.YEAR);

        List<Employee> employees = employeeRepository.findAll();

        if (!employees.isEmpty()){
            for(Employee e: employees){
                List<WorkReport> list = workReportRepository.findByTabelIDAndBeginPeriodAndEndPeriod(e.getTabelID(), beginPeriod,endPeriod);
                WorkReport report = list.get(list.size()-1);
                reports.add(report);
            }
        }
        return reports;
    }

    @Override
    public void deleteAll() {
        log.info("Clear table reports");
        workReportRepository.deleteAll();
    }

    @Override
    public void sendEmailOfPeriod(WorkReport report) {
        Employee employee = employeeRepository.findByTabelID(report.getTabelID());
        Mail mail = new Mail();
        //mail.setFrom("tetyana.loban@gmail.com");
        mail.setTo(employee.getEmail());
        mail.setSubject("Report period: "+report.getBeginPeriod()+" - "+report.getEndPeriod());
        String text = employee.getFirstName()+"."+employee.getLastName()+"\n"
                +"period: "+report.getBeginPeriod()+"-"+report.getEndPeriod()+"\n"
                +"count of work days: "+ report.getCountDay()+"\n"
                +"RESULT salary: "+ report.getSalaryPeriod();

        mail.setContent(text);

        emailService.sendSimpleMessage(mail);
    }


}
