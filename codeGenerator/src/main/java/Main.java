import fieldTypeChain.*;
import write.WriterStrategy;
import write.WriterStrategyJavaFile;

import java.io.IOException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IOException {
        FieldType fieldType = new FieldTypeBoolean();
        fieldType.linkWith(new FieldTypeInteger())
                .linkWith(new FieldTypeFloat())
                .linkWith(new FieldTypeString())
                .linkWith(new FieldTypeLocalDate())
                .linkWith(new FieldTypeLocalDateTime())
                .linkWith(new FieldTypeClass());
        FieldFormat fieldFormat = new FieldFormat(2,9,13,14, 3);
        SourceReader sourceReader = new SourceReaderFromExcel("/home/alexander/Учеба/МФТИ/3 семестр/Java/codeGenerator/src/main/resources/AFS.xlsx", fieldType, fieldFormat);
        Collection<ClassSource> classSources = sourceReader.readSource();
        WriterStrategy writerStrategy = new WriterStrategyJavaFile("/home/alexander/Загрузки/java");
        Writer writer = new Writer(writerStrategy);
        writer.write(classSources);
    }
}
