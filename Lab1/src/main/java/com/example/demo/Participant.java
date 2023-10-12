package com.example.demo;
import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;

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
    private String name;

    private int ranking;

    @ManyToOne
    @JoinColumn(name = "tournament")
    private Tournament tournament;
}
