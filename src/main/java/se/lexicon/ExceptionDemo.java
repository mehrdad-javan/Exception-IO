package se.lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExceptionDemo {

    public static void main(String[] args) {

        // checked-exception
        /*Path path = Paths.get("dir/skills.txt");
        Files.newBufferedReader(path);*/

        // unchecked-exception
        /*int[] numbers = {1, 2, 3, 4};
        System.out.println(numbers[5]);*/

        // System.out.println(getDate());
        // System.out.println("DONE");

        // readTextFile();
        // readImage();
        writeTextToFileS2();
    }


    // Catching unchecked-exception
    public static LocalDate getDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        while (true) {
            try {
                // protected code
                System.out.println("Enter a date (yyyy-MM-dd): ");
                String input = scanner.nextLine();
                date = LocalDate.parse(input);
                break;
            } catch (DateTimeParseException e) {
                // exceptions
                System.out.println("Invalid date format. Please enter a date in yyyy-MM-dd format." + e.getMessage());
            }
        }
        return date;
    }

    public static Supplier<LocalDate> getFunctionalDate = () -> {
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        while (true) {
            try {
                // protected code
                System.out.println("Enter a date (yyyy-MM-dd): ");
                String input = scanner.nextLine();
                date = LocalDate.parse(input);
                break;
            } catch (DateTimeParseException e) {
                // exceptions
                System.out.println("Invalid date format. Please enter a date in yyyy-MM-dd format." + e.getMessage());
            }
        }
        return date;
    };

    // Catching checked-exception
    public static void readTextFile() {
        // java.io
        // java.nio

        try {
            Path relativePath = Paths.get("dir/skills.txt");
            //Path relativePath = Paths.get("non_existing_filename.txt");
            // Path invalidPath = Paths.get("dir:/<invalid>|path?.txt");
            // Path absolutePath = Paths.get("D:/lexicon/g51/Exception-IO/dir/skills.txt");
            BufferedReader reader = Files.newBufferedReader(relativePath);
            Stream<String> stringStream = reader.lines();
            List<String> strings = stringStream.collect(Collectors.toList());
            strings.forEach(System.out::println);
            // Use case: Suitable for medium-sized files (10MB to 100MB)

            System.out.println("----------------------");
            List<String> stringList = Files.readAllLines(relativePath);
            stringList.forEach(System.out::println);
            // Use case: Appropriate for smaller files (0MB to 10MB)

            Files.lines(relativePath).forEach(System.out::println);
            // Use case: Ideal for very large files ( > 100 MB)


        } catch (InvalidPathException e) {
            System.out.println("Invalid Path Exception");
            System.out.println("The file path is not valid:" + e.getMessage());
        } catch (NoSuchFileException e) {
            System.out.println("File not found.");
            System.out.println("The file path is not found:" + e.getFile());
        } catch (IOException e) {
            System.out.println("An I/O exception occurred: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Permission denied: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void readImage() {
        try {
            Path sourcePath = Paths.get("source/java_logo.png");
            Path destinationPath = Paths.get("destination/java_logo.png");
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copy Operation is Done!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("File System Error: " + e.getFile());
        } catch (IOException e) {
            System.out.println("An I/O exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void writeTextToFile() {

        BufferedWriter bufferedWriter = null;
        try {
            Path relativePath = Paths.get("dir/skills.txt");

            bufferedWriter = Files.newBufferedWriter(relativePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            bufferedWriter.write("SQL");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An I/O exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("finally block has been executed!");
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }



    public static void writeTextToFileS2() {

        try (
                BufferedWriter bufferedWriter = Files.newBufferedWriter(
                        Paths.get("dir/skills.txt"),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
                )
        {


            bufferedWriter.write("SQL");
            bufferedWriter.newLine();
            //bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An I/O exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("finally block has been executed!");
        }
    }

}
