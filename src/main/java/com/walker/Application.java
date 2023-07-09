package com.walker;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.walker.utilities.isSpecialCharacter.WordContainsSpecialCharacter;
import static com.walker.utilities.isSpecialCharacter.isSpecialCharacter;

public class Application {
    private static final String WORK_DIR = "src/main/resources/";
    private static final char[] SPECIAL_CHARACTERS = {'ö', 'ü', 'ä', 'ß'};
    private static final String[] REPLACEMENTS = {"oe", "ue", "ae", "ss"};

    public static void main(String[] args) {
        String file = WORK_DIR + "german-example.txt";

        printSpecialCharacterStatistics(file);
        writeLinesWithSpecialCharacters(file);
        writeInternationalizedText(file);
        countWordsWithSpecialCharacters(file);
    }

    private static void printSpecialCharacterStatistics(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long totalCharacters = reader.lines()
                    .flatMapToInt(String::chars)
                    .filter(c -> !Character.isWhitespace(c))
                    .count();

            long specialCharacterCount = reader.lines()
                    .flatMapToInt(String::chars)
                    .filter(c -> isSpecialCharacter((char) c))
                    .count();

            double specialCharacterPercentage = (double) specialCharacterCount / totalCharacters * 100;

            System.out.println("Special Character Statistics:");
            System.out.println("Total Characters: " + totalCharacters);
            System.out.println("Special Characters: " + specialCharacterCount);
            System.out.println("Special Characters Percentage: " + specialCharacterPercentage + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLinesWithSpecialCharacters(String file) {
        String outputFile = WORK_DIR + "lines-with-special-characters.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            reader.lines()
                    .filter(line -> line.chars().anyMatch(c -> isSpecialCharacter((char) c)))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("Lines with special characters written to lines-with-special-characters.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeInternationalizedText(String file) {
        String outputFile = WORK_DIR + "internationalized.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            reader.lines()
                    .map(line -> {
                        for (int i = 0; i < SPECIAL_CHARACTERS.length; i++) {
                            line = line.replace(Character.toString(SPECIAL_CHARACTERS[i]), REPLACEMENTS[i]);
                        }
                        return line;
                    })
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("Internationalized text written to internationalized.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void countWordsWithSpecialCharacters(String file) {
        String outputFile = WORK_DIR + "count-by-words-with-special.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            Map<String, Integer> wordCountMap = reader.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> WordContainsSpecialCharacter(word))
                    .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

            wordCountMap.forEach((word, count) -> {
                try {
                    writer.write(word + ": " + count);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Word count with special characters written to count-by-words-with-special.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
