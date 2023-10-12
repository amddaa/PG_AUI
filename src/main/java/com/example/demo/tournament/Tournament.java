package com.example.demo.tournament;
import com.example.demo.participant.Participant;
import lombok.*;
import java.util.List;
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
    private UUID uuid;

    private String name;

    private int requiredRanking;

    @OneToMany(mappedBy = "tournament")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Participant> participants;
}
