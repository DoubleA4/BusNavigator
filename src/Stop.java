public record Stop(
        String id,
        String name
) {
    @Override
    public String toString() {
        return "(" + id + ")" + name;
    }
}
