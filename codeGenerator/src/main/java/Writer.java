import write.WriterStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class Writer {
    private final WriterStrategy writerStrategy;

    public Writer(WriterStrategy writerStrategy) {
        this.writerStrategy = writerStrategy;
    }

    public void write(Collection<ClassSource> classSources){
        for (ClassSource classSource : classSources) {
            try {
                writerStrategy.execute(classSource.generationCode(), classSource.getName());
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
