package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String name;
    private String email;


    @ManyToMany( mappedBy = "attendees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;
}
