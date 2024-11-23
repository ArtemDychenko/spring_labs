package org.example;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Director> directors = new ArrayList<>();

        Director quentin_tarantino = new Director("Quentin Tarantino", 1963);
        Director paolo_sorrentino = new Director("Paolo Sorrentino", 1970);
        Director yorgos_lanthimos = new Director("Yorgos Lanthimos", 1973);
        Director akira_kurosawa = new Director("Akira Kurosawa", 1910);

        directors.add(quentin_tarantino);
        directors.add(paolo_sorrentino);
        directors.add(yorgos_lanthimos);
        directors.add(akira_kurosawa);

        Movie movie1 = new Movie.Builder( "Pulp Fiction", 1994)
                .time(149)
                .genre("Crime/Thriller")
                .build();
        quentin_tarantino.addMovie(movie1);

        MovieDTO movieDTO = new MovieDTO(movie1);

        Movie movie2 = new Movie.Builder( "Kill Bill", 2004)
                .time(111)
                .genre("Action/Thriller")
                .build();
        quentin_tarantino.addMovie(movie2);

        Movie movie3 = new Movie.Builder( "The Great Beauty", 2013)
                .time(144)
                .genre("Comedy/Drama")
                .build();
        paolo_sorrentino.addMovie(movie3);

        Movie movie4 = new Movie.Builder("The Hand of God", 2021)
                .time(130)
                .genre("Comedy/Drama")
                .build();
        paolo_sorrentino.addMovie(movie4);

        Movie movie5 = new Movie.Builder("Kinds of Kindness", 2024)
                .time(165)
                .genre("Comedy/Drama")
                .build();
        yorgos_lanthimos.addMovie(movie5);



        System.out.println("task 1");

        System.out.println(movie1);
        System.out.println(movieDTO);


        System.out.println("-".repeat(40));
        System.out.println("task 2");

        directors.forEach(director -> {
            System.out.println(director);
            director.getMovies().forEach(element -> {
                System.out.println("  " + element);
            });
        });

        System.out.println("-".repeat(40));
        System.out.println("task 3");



        Set<Movie> allMovies = directors.stream()
                .flatMap(director -> director.getMovies().stream())
                .collect(Collectors.toSet());

        // Drukowanie wszystkich elementów z użyciem drugiego potoku Stream
        allMovies.forEach(System.out::println);
        System.out.println("-".repeat(40));
        System.out.println("task 4");

        allMovies.stream()
                .filter(element -> element.getTime() > 120) // Filtrujemy elementy, których cena jest większa niż 50
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName())) // Sortujemy elementy według nazwy
                .forEach(System.out::println); // Drukujemy przefiltrowane i posortowane elementy

        System.out.println("-".repeat(40));
        System.out.println("task 5");

        List<MovieDTO> movieDTOS = allMovies.stream()
                .map(movie -> new MovieDTO(movie))
                .sorted()
                .collect(Collectors.toList());

        movieDTOS.stream()
                .forEach(System.out::println);

        System.out.println("-".repeat(40));
        System.out.println("task 6");

        //Serializacja kolekcji
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("directors.ser"))) {
            out.writeObject(directors);
            System.out.println("Serialized data is saved in directors.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Deserializacja pliku
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("directors.ser"))) {
            List<Director> deserializedDirectors = (List<Director>) in.readObject();
            System.out.println("Deserialized data:");

            deserializedDirectors.forEach(director -> {
                System.out.println(director.getName() + " Age: " + director.getAge());
                director.getMovies().forEach(System.out::println);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("-".repeat(40));
        System.out.println("task 7");

        ForkJoinPool customThreadPool = new ForkJoinPool(2);

        try {
            // Wykonanie równoległego przetwarzania przy użyciu niestandardowej puli wątków
            customThreadPool.submit(() ->
                    directors.parallelStream().forEach(category -> {
                        System.out.println("Processing director: " + category.getName() + " using thread: " + Thread.currentThread().getName());
                        category.getMovies().forEach(element -> {
                            try {
                                // Symulacja obciążenia poprzez opóźnienie
                                Thread.sleep(1000);
                                System.out.println("Movie: " + element + " processed by thread: " + Thread.currentThread().getName());
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        });
                    })
            ).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customThreadPool.shutdown();
            try {
                if (!customThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    customThreadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                customThreadPool.shutdownNow();
            }
        }
    }

}
