package com.walker.utilities;



public class isSpecialCharacter {
    private static final char[] SPECIAL_CHARACTERS = {'ö', 'ü', 'ä', 'ß'};

    public static boolean isSpecialCharacter(char c) {
        for (char specialChar : SPECIAL_CHARACTERS) {
            if (specialChar == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean LineContainsSpecialCharacter(String line) {
        for (char specialChar : SPECIAL_CHARACTERS) {
            if (line.contains(Character.toString(specialChar))) {
                return true;
            }
        }
        return false;
    }

    public static boolean WordContainsSpecialCharacter(String word) {
        for (char specialChar : SPECIAL_CHARACTERS) {
            if (word.contains(Character.toString(specialChar))) {
                return true;
            }
        }
        return false;
    }
}

