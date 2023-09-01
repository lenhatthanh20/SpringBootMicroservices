package com.lenhatthanh.usersservice.shared;

public class StringHelpers {
    public static String[] splitStringWithColon(String string) {
        String[] parts = string.split(":", 2);

        return new String[] { parts[0].trim(), parts[1].trim() };
    }
}
