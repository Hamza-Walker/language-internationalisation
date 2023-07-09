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

    public static boolean containsSpecialCharacter(String line) {
        for (char specialChar : SPECIAL_CHARACTERS) {
            if (line.contains(Character.toString(specialChar))) {
                return true;
            }
        }
        return false;
    }
}

