package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.Department;
import goit.finalproject.rest.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department save(Department entity) {
        return departmentRepository.save(entity);
    }

    @Override
    public Department getById(long id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void delete(long id) {
        departmentRepository.delete(id);
    }
}
