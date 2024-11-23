package com.example.aui;

import com.example.aui.service.DirectorService;
import com.example.aui.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final Scanner scanner;

    @Autowired
    public UserCommandLineRunner(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) {
        String command;
        System.out.println("Hi! The Movie Management System is here!");
        do {
            System.out.println("Available commands: list, add, delete, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "list":
                    listElements();
                    break;
                case "add":
                    addElement();
                    break;
                case "delete":
                    deleteElement();
                    break;
                case "exit":
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equalsIgnoreCase("exit"));
    }

    private void listElements() {
        System.out.println("Movies:");
        movieService.printAllMovies();

        System.out.println("Directors:");
        directorService.printAllDirectors();
    }


    private void addElement() {
        System.out.print("Enter the type (movie/director): ");
        String type = scanner.nextLine().trim().toLowerCase();

        if (type.equals("movie")) {
            System.out.print("Enter movie name: ");
            String name = scanner.nextLine();

            System.out.print("Enter date of release: ");
            int dateOfRelease = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter movie time (in minutes): ");
            int time = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();

            System.out.print("Enter director name: ");
            String directorName = scanner.nextLine();


            Movie movie = movieService.createMovie(directorName, name, dateOfRelease, time, genre);

            movieService.saveMovie(movie);
            System.out.println("Movie added successfully!");

        } else if (type.equals("director")) {
            System.out.print("Enter director name: ");
            String directorName = scanner.nextLine();

            System.out.print("Enter year of birth: ");
            int yearOfBirth = Integer.parseInt(scanner.nextLine());

            Director director = new Director(directorName, yearOfBirth);
            directorService.saveDirector(director);
            System.out.println("Director added successfully!");

        } else {
            System.out.println("Invalid type. Please enter movie or director.");
        }
    }

    private void deleteElement() {
        System.out.print("Enter the type (movie/director): ");
        String type = scanner.nextLine().trim().toLowerCase();

        if (type.equals("movie")) {
            System.out.print("Enter movie name to delete: ");
            String movieName = scanner.nextLine();

            try {
                movieService.deleteMovieByName(movieName);
                System.out.println("Deleted movie: " + movieName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else if (type.equals("director")) {
            System.out.print("Enter director name to delete: ");
            String directorName = scanner.nextLine();

            try {
                directorService.deleteDirectorByName(directorName);
                System.out.println("Deleted director: " + directorName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Invalid type. Please enter movie or director.");
        }
    }
}
