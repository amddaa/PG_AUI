package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class AuiApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(AuiApplication.class, args);

		Map<Tournament, List<Participant>> tournaments = getCreatedProfessionsAndCharacters();
		printCategories(tournaments);

		System.out.println("\n");

		pipelinePrint(tournaments);

		System.out.println("\n");

		pipelineFilterAndSort(tournaments);

		System.out.println("\n");

		transformIntoDtoAndPrint(tournaments);

		System.out.println("\n");

		serializeDeserializeAndPrint(tournaments);

		System.out.println("\n");

		executeParallelTasks(tournaments);
	}

	public static Map<Tournament, List<Participant>> getCreatedProfessionsAndCharacters() {
		Tournament tournament1 = Tournament.builder().name("Bardzo wazny turniej").requiredRanking(2000).build();
		Tournament tournament2 = Tournament.builder().name("Malo wazny turniej").requiredRanking(1000).build();

		Participant participant1 = Participant.builder().name("Jan Kowalski").ranking(2500).tournament(tournament1).build();
		Participant participant2 = Participant.builder().name("Tomasz Niekowalski").ranking(2300).tournament(tournament1).build();
		Participant participant3 = Participant.builder().name("Marcin Duda").ranking(1100).tournament(tournament2).build();
		Participant participant4 = Participant.builder().name("Adam Adamowicz").ranking(1000).tournament(tournament2).build();

		Map<Tournament, List<Participant>> categories = new HashMap<>();
		categories.put(tournament1, new ArrayList<>());
		categories.put(tournament2, new ArrayList<>());

		categories.get(tournament1).add(participant1);
		categories.get(tournament1).add(participant2);
		categories.get(tournament2).add(participant3);
		categories.get(tournament2).add(participant4);

		return categories;
	}

	public static void printCategories(Map<Tournament, List<Participant>> categories) {
		categories.forEach((category, elements) -> {
			System.out.println(category);
			elements.forEach(System.out::println);
		});
	}

	public static void pipelinePrint(Map<Tournament, List<Participant>> categories) {
		Set<Participant> streamSet = categories.values().stream().flatMap(List::stream).collect(Collectors.toSet());
		streamSet.forEach(System.out::println);
	}

	public static void pipelineFilterAndSort(Map<Tournament, List<Participant>> categories) {
		Stream<Participant> stream = categories.values().stream().flatMap(List::stream)
				.filter(value -> value.getRanking() == 5).sorted(Comparator.comparing(Participant::getName));
		stream.forEach(System.out::println);
	}

	public static void transformIntoDtoAndPrint(Map<Tournament, List<Participant>> categories)
	{
		Stream<Participant> stream = categories.values().stream().flatMap(List::stream);

		List<ParticipantDto> participantDtos = new ArrayList<>(stream
				.map(value -> ParticipantDto.builder().name(value.getName()).ranking(value.getRanking()).tournament(value.getTournament().getName()).build())
				.toList());

		participantDtos.sort(Comparator.comparing(ParticipantDto::getName));

		participantDtos.forEach(System.out::println);
	}

	public static void serializeDeserializeAndPrint(Map<Tournament, List<Participant>> categories)
	{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("categories.bin"))) {
			outputStream.writeObject(categories);
			System.out.println("Serialized and saved");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("categories.bin"))) {
			Map<Tournament, List<Participant>> container = (Map<Tournament, List<Participant>>) inputStream.readObject();
			printCategories(container);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void executeParallelTasks(Map<Tournament, List<Participant>> categories) throws ExecutionException, InterruptedException {
		ForkJoinPool pool = new ForkJoinPool(10);

		categories.forEach((category, elements) -> {
			try {
				pool.submit(() -> {
					System.out.println(category);
					elements.stream()
							.parallel()
							.forEach(participant -> {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}

						System.out.println(participant);
					});
				}).get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
		});

		pool.shutdown();
	}
}
