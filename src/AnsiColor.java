public class AnsiColor {

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

    public static String reset() {
        return "\u001B[0m";
    }
}