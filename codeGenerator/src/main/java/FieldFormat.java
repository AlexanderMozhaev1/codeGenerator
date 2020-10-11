public class FieldFormat {
    private final int nameIndex;
    private final int descriptionIndex;
    private final int typeIndex;
    private final int multiplicityIndex;
    private final int rowIndex;

    public FieldFormat(int nameIndex, int descriptionIndex, int typeIndex, int multiplicityIndex, int rowIndex) {
        this.nameIndex = nameIndex;
        this.descriptionIndex = descriptionIndex;
        this.typeIndex = typeIndex;
        this.multiplicityIndex = multiplicityIndex;
        this.rowIndex = rowIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptionIndex() {
        return descriptionIndex;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public int getMultiplicityIndex() {
        return multiplicityIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
