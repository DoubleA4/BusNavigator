package id.klacak.bus_navigator;

/** Utility methods for creating ANSI terminal color sequences. */
public class AnsiColor {

    /** Creates an ANSI color utility. */
    public AnsiColor() {
    }

    /**
     * Converts an RGB hexadecimal color into an ANSI 24-bit background escape sequence.
     *
     * @param hexColor color in {@code #RRGGBB} or {@code RRGGBB} format
     * @return ANSI escape sequence that sets the background color
     * @throws NumberFormatException if the color contains non-hexadecimal digits
     * @throws StringIndexOutOfBoundsException if the color does not contain six hexadecimal digits
     */
    // Helper method to turn a "#RRGGBB" hex string into an ANSI background sequence
    public static String bgHex(String hexColor) {
        // Parse hex string (e.g. "#EB3B5A" or "EB3B5A")
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.substring(1);
        }
        int r = Integer.parseInt(hexColor.substring(0, 2), 16);
        int g = Integer.parseInt(hexColor.substring(2, 4), 16);
        int b = Integer.parseInt(hexColor.substring(4, 6), 16);

        return String.format("\u001B[48;2;%d;%d;%dm", r, g, b);
    }

    /**
     * Converts an RGB hexadecimal color into an ANSI 24-bit foreground escape sequence.
     *
     * @param hexColor color in {@code #RRGGBB} or {@code RRGGBB} format
     * @return ANSI escape sequence that sets the foreground color
     * @throws NumberFormatException if the color contains non-hexadecimal digits
     * @throws StringIndexOutOfBoundsException if the color does not contain six hexadecimal digits
     */
    // Helper method for text (foreground) color
    public static String fgHex(String hexColor) {
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.substring(1);
        }
        int r = Integer.parseInt(hexColor.substring(0, 2), 16);
        int g = Integer.parseInt(hexColor.substring(2, 4), 16);
        int b = Integer.parseInt(hexColor.substring(4, 6), 16);

        return String.format("\u001B[38;2;%d;%d;%dm", r, g, b);
    }

    /**
     * Returns the ANSI escape sequence that restores terminal formatting to its default.
     *
     * @return ANSI reset escape sequence
     */
    public static String reset() {
        return "\u001B[0m";
    }
}