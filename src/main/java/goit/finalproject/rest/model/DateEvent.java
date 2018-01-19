package goit.finalproject.rest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="dateevents")
public class DateEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated dateEvent ID")
    private Long id;

    @Column(name="dateevent")
    @ApiModelProperty(notes = "Date of event")
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    @ApiModelProperty(notes = "Type of event")
    private Event event;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "All departments take part in the event")
    private Set<Department> departments;

    //1
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "All employees take part in the event")
    private Set<Employee> employees;

    //2
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "dateevents_employees",
//    joinColumns = { @JoinColumn(name = "events_id") },
//    inverseJoinColumns = { @JoinColumn(name = "employees_id") })
//    private Set<Employee> employees;

    //3
//    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "events")
//    private Set<Employee> employees;

    public DateEvent() {
    }

    public DateEvent(String date, Event event) {
        this.date = date;
        this.event = event;
    }

    public DateEvent(String date, Event event, Set<Department> departments, Set<Employee> employees) {
        this.date = date;
        this.event = event;
        this.departments = departments;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "DateEvent{" +
                "date=" + date +
                ", event=" + event +
                ", departments=" + departments +
//                ", employees=" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateEvent dateEvent = (DateEvent) o;

        if (id != null ? !id.equals(dateEvent.id) : dateEvent.id != null) return false;
        if (date != null ? !date.equals(dateEvent.date) : dateEvent.date != null) return false;
        return event != null ? event.equals(dateEvent.event) : dateEvent.event == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
