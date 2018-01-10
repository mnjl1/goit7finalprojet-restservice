package goit.finalproject.rest.repository;

import goit.finalproject.rest.model.DateStatus;
import goit.finalproject.rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateStatusRepository extends JpaRepository<DateStatus, Long> {

    List<DateStatus> findByEmployee(Employee employee);
    List<DateStatus> findByDateAndEmployee(String date, Employee employee);
    List<DateStatus> findByDate(String date);
}
