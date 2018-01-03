package goit.finalproject.rest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "eventName")
    private String eventName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event")
    private Set<Employee> employees;

    public Event(){

    }

    public Event(int id, String event, Set<Employee> employees) {
        this.id = id;
        this.eventName = event;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return eventName;
    }

    public void setEventName(String event) {
        this.eventName = event;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", event='" + eventName + '\'' +
                ", employees=" + employees +
                '}';
    }
}
