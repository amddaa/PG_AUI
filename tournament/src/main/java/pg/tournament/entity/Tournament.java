package pg.tournament.entity;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name="tournaments")
public class Tournament implements Serializable{
    @Id
    private UUID id;

    private String name;

    @Column(nullable = true)
    private int requiredRank;
}
