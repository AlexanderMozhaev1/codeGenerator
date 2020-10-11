package write;

import java.io.FileWriter;
import java.io.IOException;

public class WriterStrategyJavaFile implements WriterStrategy{
    private final String dirPath;

    public WriterStrategyJavaFile(String dirPath) {
        this.dirPath = dirPath;
    }

    @Override
    public void execute(String code, String name) {
        try(FileWriter writer = new FileWriter(dirPath + "/" + name + ".java", false))
        {
            writer.write(code);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
