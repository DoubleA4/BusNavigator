package id.klacak.bus_navigator;

/** A transit route designated as a feeder service. */
public class Feeder extends Route{
    /**
     * Creates a feeder route with the supplied names and display colors.
     *
     * @param shortName route code
     * @param longName descriptive route name
     * @param color route background color in hexadecimal format
     * @param textColor route text color in hexadecimal format
     */
    public Feeder(String shortName, String longName, String color, String textColor) {
        super(shortName, longName, color, textColor);
    }
}
