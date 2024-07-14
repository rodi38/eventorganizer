package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import lombok.*;
import soft.rodi38.eventorganizer.model.enums.DonationType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    private DonationType donationType;

    private BigDecimal value;

    @ManyToOne
    private Attendee attendee;

    @ManyToOne
    private Event event;

    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;


}
