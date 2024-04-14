import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderAndWrite {
    public static String read(String file_path) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(file_path)));
        return text;
    }
}
