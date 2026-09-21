public record Route(
        String id,
        String shortName,
        String longName,
        String color,
        String textColor
) {
    @Override
    public String toString() {
        return "[" + id + "]" + "(" + shortName + ")" + longName;
    }
}
