package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;



    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    @ManyToMany()
    private List<Attendee> attendees;


    @ManyToOne()
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    
}
