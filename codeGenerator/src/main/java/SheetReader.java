import fieldTypeChain.FieldType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

public class SheetReader {
    private final RowReader rowReader;
    private final FieldFormat fieldFormat;
    private final FieldType fieldType;

    public SheetReader(FieldFormat fieldFormat, FieldType fieldType) {
        rowReader = new RowReader(fieldFormat);
        this.fieldFormat = fieldFormat;
        this.fieldType = fieldType;
    }

    public ClassSource readSheet(XSSFSheet sheet, String listName){
        if (sheet == null) return null;
        ArrayList<Field> fields = new ArrayList<>();
        for (int rowIndex = fieldFormat.getRowIndex(); rowIndex < sheet.getLastRowNum(); rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            Field field = rowReader.readRow(row);
            if(field == null) continue;
            fields.add(field);
        }
        return new ClassSourceFromExcel(listName, fields, fieldType);
    }
}
