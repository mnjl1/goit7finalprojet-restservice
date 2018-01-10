package goit.finalproject.rest.repository;

import goit.finalproject.rest.model.DateEvent;
import goit.finalproject.rest.model.Department;
import goit.finalproject.rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateEventRepository extends JpaRepository<DateEvent, Long> {

    List<DateEvent> findByEmployees(List<Employee> employees);
    List<DateEvent> findByEmployeesAndDate(List<Employee> employees, String date);
    List<DateEvent> findByDepartments(List<Department> departments);

    DateEvent findByDate(String date);
}
