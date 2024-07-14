package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import soft.rodi38.eventorganizer.model.enums.DonationType;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    private String code;

    @NotBlank
    private DonationType donationType;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    private boolean hasSold;


    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;






}
