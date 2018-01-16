package goit.finalproject.rest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="positions")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated position ID")
    private Long id;

    @Column(name="name")
    @ApiModelProperty(notes = "Name of position")
    private String name;

    @Column(name="salary")
    @ApiModelProperty(notes = "Salary of position")
    private Float salary;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
//    @ApiModelProperty(notes = "Employees with this position")
//    private Set<Employee> employees;


    public Position() {
    }

    public Position(String name, Float salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Long getId() {

        return id;
    }

//    public Set<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Set<Employee> employees) {
//        this.employees = employees;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
