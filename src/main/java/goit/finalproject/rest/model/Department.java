package goit.finalproject.rest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="departments")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated department ID")
    private Long id;

    @Column(name="name")
    @ApiModelProperty(notes = "Name of department")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "departments")
    @ApiModelProperty(notes = "List of event with date for this department")
    private List<DateEvent> dateevents;

    @OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL }, mappedBy = "department")
    @ApiModelProperty(notes = "Employees in this department")
    private Set<Employee> employees;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DateEvent> getDateevents() {
        return dateevents;
    }

    public void setDateevents(List<DateEvent> dateevents) {
        this.dateevents = dateevents;
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
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
