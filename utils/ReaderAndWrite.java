package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderAndWrite {
    public static String read(String file_path) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(file_path)));
        return text;
    }
    public static void write(String content, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
