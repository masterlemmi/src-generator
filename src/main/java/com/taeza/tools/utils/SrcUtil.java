package com.taeza.tools.utils;

public class SrcUtil {

    private static final String regex = "^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+[0-9a-z_]$";

    public static boolean isValidJavaPackage(String pckageName) {
        return pckageName.matches(regex);
    }
}
