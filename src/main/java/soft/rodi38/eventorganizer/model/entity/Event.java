package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Event {
    @Id
    private String id;
    private String name;
    private String location;
    private String date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Attendee> attendee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Attendee> getAttendee() {
        return attendee;
    }

    public void setAttendee(Set<Attendee> attendee) {
        this.attendee = attendee;
    }
}
