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
}
