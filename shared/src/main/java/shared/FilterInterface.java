package shared;

import java.io.File;
import java.io.IOException;

public interface FilterInterface {
    public File converFile(File file) throws IOException;
}
