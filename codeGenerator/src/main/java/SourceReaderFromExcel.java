import fieldTypeChain.FieldType;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SourceReaderFromExcel implements SourceReader{
    private final String fileName;
    private final FieldType fieldType;
    private final FieldFormat fieldFormat;

    public SourceReaderFromExcel(String fileName, FieldType fieldType, FieldFormat fieldFormat) {
        this.fileName = fileName;
        this.fieldType = fieldType;
        this.fieldFormat = fieldFormat;
    }

    @Override
    public Collection<ClassSource> readSource() throws IOException {
        ZipSecureFile.setMinInflateRatio(0);
        XSSFWorkbook excelBook = new XSSFWorkbook(new FileInputStream(fileName));
        ArrayList<ClassSource> classSources = new ArrayList<>();
        SheetReader sheetReader = new SheetReader(fieldFormat, fieldType);
        for (int index = 0; index < excelBook.getActiveSheetIndex(); index++) {
            String listName = excelBook.getSheetName(index);
            if (listName.matches("[A-Z].*[A-Za-z0-9].*")) {
                XSSFSheet sheet = excelBook.getSheet(listName);
                ClassSource classSource = sheetReader.readSheet(sheet, listName);
                if (classSource == null) continue;
                classSources.add(classSource);
            }
        }
        return classSources;
    }
}
