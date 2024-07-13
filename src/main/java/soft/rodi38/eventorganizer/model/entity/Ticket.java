package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import soft.rodi38.eventorganizer.model.enums.DonationType;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    private String code;

    private DonationType donationType;

    @ManyToOne
    private Attendee attendee;

    @ManyToOne
    private Event event;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;






}
