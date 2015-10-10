package com.codery.cheats.priston;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class ASCIIUtils {

    static int getAsciiCode(String key) {
        if (key.length() == 1) {
            return key.charAt(0);
        }

        if (key.length() == 2 && (key.startsWith("F") || key.startsWith("f"))) {
            return getAsciiFunctionKey(key);
        }

        throw new RuntimeException("Invalid key: " + key);
    }


    private static int getAsciiFunctionKey(String fkey) {
        for (int i = 0x70, j = 1; i <= 0x81; i++, j++) {
            if (new StringBuilder("F").append(j).toString().equalsIgnoreCase(fkey)) {
                System.out.println("  ASCII code found! Code: " + i);
                return i;
            }
        }
        throw new RuntimeException("Ascii key \"" + fkey + "\" could not be found.");
    }
}
