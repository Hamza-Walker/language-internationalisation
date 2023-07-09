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
}