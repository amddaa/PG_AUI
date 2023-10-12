package com.example.demo.participant;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ParticipantDto {
    private String surname;
    private int rank;
    private String tournament;
}
