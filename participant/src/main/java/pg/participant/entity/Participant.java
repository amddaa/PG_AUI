package pg.participant.entity;
import pg.tournament.entity.Tournament;
import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "participants")
public class Participant implements Serializable {
    @Id
    private UUID id;
    private String surname;
    private int rank;
    @ManyToOne
    @JoinColumn(name = "tournament")
    private Tournament tournament;
}
