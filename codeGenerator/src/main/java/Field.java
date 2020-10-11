public class Field {
    private final String name;
    private final String description;
    private final String type;
    private final char multiplicityMin;
    private final char multiplicityMax;

    public Field(String name, String description, String type, char multiplicityMin, char multiplicityMax) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.multiplicityMin = multiplicityMin;
        this.multiplicityMax = multiplicityMax;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getMultiplicityMin() {
        return multiplicityMin;
    }

    public int getMultiplicityMax() {
        return multiplicityMax;
    }
}
