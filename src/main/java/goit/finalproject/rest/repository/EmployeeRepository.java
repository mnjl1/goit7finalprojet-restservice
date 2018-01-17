package goit.finalproject.rest.repository;

import goit.finalproject.rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//
//    @Query("from Employee e where e.id= :id")
//    Employee testFunc(@Param("id") long id);

    Employee findByTabelID(Long tabelID);

    //@Query("from Employee e where e.email = :email")
    List<Employee> findByEmail(String email);

}
