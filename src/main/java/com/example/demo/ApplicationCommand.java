package com.example.demo;

import com.example.demo.participant.Participant;
import com.example.demo.participant.service.impl.ParticipantDefaultService;
import com.example.demo.tournament.Tournament;
import com.example.demo.tournament.service.impl.TournamentDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {
    private final TournamentDefaultService tournamentDefaultService;

    private final ParticipantDefaultService participantDefaultService;

    @Autowired
    public ApplicationCommand(
            TournamentDefaultService tournamentDefaultService,
            ParticipantDefaultService participantDefaultService
    ){
        this.tournamentDefaultService = tournamentDefaultService;
        this.participantDefaultService = participantDefaultService;
    }

    @Override
    public void run(String... args) throws Exception {
        printHelp();

        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while(true){
            command = scanner.next();
            System.out.println(command);
            switch (command)
            {
                case "help" -> {
                    printHelp();
                }
                case "get_tournaments" -> {
                    tournamentDefaultService.findAll().forEach(System.out::println);
                }
                case "get_participants" -> {
                    participantDefaultService.findAll().forEach(System.out::println);
                }
                case "put_participant" -> {
                    System.out.println("input tournament's UUID");
                    UUID uuid = UUID.fromString(scanner.next());
                    Tournament t = tournamentDefaultService.find(uuid).orElseThrow();

                    System.out.println("input surname:");
                    String surname = scanner.next();

                    System.out.println("input ranking");
                    int rank = scanner.nextInt();

                    participantDefaultService.create(Participant.builder()
                            .tournament(t)
                            .surname(surname)
                            .rank(rank)
                            .uuid(UUID.randomUUID())
                            .build());
                }
                case "delete_participant" -> {
                    System.out.println("input participant's UUID");
                    UUID uuid = UUID.fromString(scanner.next());
                    participantDefaultService.delete(uuid);
                }
                case "quit" -> {
                    break main_loop;
                }
            }
        }
    }

    private void printHelp()
    {
        System.out.println("Available commands:");
        System.out.println("""
                help
                get_tournaments
                get_participants
                put_participant
                delete_participant
                quit""");
    }
}
