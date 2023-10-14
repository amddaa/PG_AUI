package com.example.demo.tournament.entity;
import com.example.demo.participant.entity.Participant;
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
    private UUID id;

    private String name;

    @Column(nullable = true)
    private int requiredRank;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Participant> participants;
}
