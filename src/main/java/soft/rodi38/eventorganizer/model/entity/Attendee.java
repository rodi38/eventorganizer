package soft.rodi38.eventorganizer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;


    @ManyToMany( mappedBy = "attendees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;
}
