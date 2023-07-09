package com.walker;

import java.io.*;

import static com.walker.utilities.isSpecialCharacter.containsSpecialCharacter;
import static com.walker.utilities.isSpecialCharacter.isSpecialCharacter;

public class Application {
    private static final String WORK_DIR = "src/main/resources/";
    private static final char[] SPECIAL_CHARACTERS = {'ö', 'ü', 'ä', 'ß'};

    public static void main(String[] args) {
        String file = WORK_DIR + "german-example.txt";

        printSpecialCharacterStatistics(file);
        writeLinesWithSpecialCharacters(file);
        writeInternationalizedText(file);
    }

    private static void printSpecialCharacterStatistics(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            int totalCharacters = 0;
            int specialCharacterCount = 0;
            int nonSpaceCharacterCount = 0;

            while ((line = reader.readLine()) != null){
                for (char c : line.toCharArray()){
                    if(Character.isWhitespace(c)) continue;
                    totalCharacters++;

                    if(isSpecialCharacter(c)) specialCharacterCount++;
                    nonSpaceCharacterCount++;
                }
            }

            double specialCharacterPercentage = (double) specialCharacterCount / nonSpaceCharacterCount * 100;

            System.out.println("Special Character Statistics:");
            System.out.println("Total Characters: " + totalCharacters);
            System.out.println("Special Characters: " + specialCharacterCount);
            System.out.println("Special Characters Percentage: " + specialCharacterPercentage + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void writeLinesWithSpecialCharacters(String file) {
        String outputFile = WORK_DIR + "Lines-with-special-characters.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while((line = reader.readLine()) !=null){
                if (containsSpecialCharacter(line)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Lines with special characters written to lines-with-special-characters.txt");

        } catch (IOException e) {
        e.printStackTrace();
        }

    }

    private static void writeInternationalizedText(String file) {
    }
}
