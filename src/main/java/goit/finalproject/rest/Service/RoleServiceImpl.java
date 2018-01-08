package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.Role;
import goit.finalproject.rest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleRepository roleRepository;

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Role getById(long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(long id) {
        roleRepository.delete(id);
    }
}
