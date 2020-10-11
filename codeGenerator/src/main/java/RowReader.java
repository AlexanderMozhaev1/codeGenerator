import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class RowReader {
    private final FieldFormat fieldFormat;

    public RowReader(FieldFormat fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    public Field readRow(XSSFRow row){
        if (row == null) return null;
        XSSFCell cell = row.getCell(fieldFormat.getNameIndex());
        if (cell == null) return null;
        String name = cell.getStringCellValue();
        if (!name.matches("[a-z].*[A-Za-z0-9].*")) return null;
        String description = getCellValue(row, fieldFormat.getDescriptionIndex());
        String type = getCellValue(row, fieldFormat.getTypeIndex());
        String multiplicity = getCellValue(row, fieldFormat.getMultiplicityIndex());
        char multiplicityMax = '1';
        char multiplicityMin = '0';
        if (!"".equals(multiplicity)) {
            multiplicityMin = multiplicity.charAt(0);
            multiplicityMax = multiplicity.charAt(multiplicity.length() - 1);
        }
        return new Field(name, description, type, multiplicityMin, multiplicityMax);
    }
    private String getCellValue(XSSFRow row, int indexCell){
        XSSFCell cell = row.getCell(indexCell);
        return cell == null ? "" : cell.getStringCellValue();
    }
}
