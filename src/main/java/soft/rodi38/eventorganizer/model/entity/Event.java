package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private String address;

    @Size(min = 20)
    private Integer maxQuantityAttendee;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    @OneToMany
    private List<Ticket> tickets;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Attendee> attendees;


    @ManyToOne()
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;
    
}
