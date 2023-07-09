package com.walker;

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
    }

    private static void writeLinesWithSpecialCharacters(String file) {
    }

    private static void writeInternationalizedText(String file) {
    }
}
