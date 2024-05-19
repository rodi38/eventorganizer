package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    private String name;
    private String location;
    private String date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Attendee> attendees;


    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;
}
