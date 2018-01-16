package goit.finalproject.rest.Service;


import goit.finalproject.rest.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(Long id);
    List<Employee> findAllEmployees();

    Employee save(Employee employee);
    void update(Employee employee);
    void delete(Long id);

    //additional
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByEmail(String email);

    Employee findByTabelID(Long tabelID);

}
