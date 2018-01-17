package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.Employee;
import goit.finalproject.rest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public Employee getById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(long id) {
        employeeRepository.delete(id);
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee findByTabelID(Long tabelID) {
        log.info("Find all Employees by tabel ID {}", tabelID);
        Employee list = employeeRepository.findByTabelID(tabelID);
        if (list!=null){
            return list;
        }
        log.info("Employees by last name {} not exist",tabelID);
        return null;
    }
}
