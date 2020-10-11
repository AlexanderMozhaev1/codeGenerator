import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface SourceReader {
    public Collection<ClassSource> readSource() throws IOException;
}
