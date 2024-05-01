import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
        CompacterAndDescompacter.compacter("input.txt");
        CompacterAndDescompacter.descompacter("input.ztxt");
    }
}