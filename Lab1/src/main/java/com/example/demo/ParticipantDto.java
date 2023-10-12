package com.example.demo;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ParticipantDto {
    private String name;
    private int ranking;
    private String tournament;
}
