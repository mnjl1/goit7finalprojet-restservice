package goit.finalproject.rest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "department")
    private String department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private Set<Employee> employees;

    public Department(){
    }

    public Department(int id, String department, Set<Employee> employees) {
        this.id = id;
        this.department = department;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", employees=" + employees +
                '}';
    }
}
