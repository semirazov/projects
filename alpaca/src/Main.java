import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.lines(new File("/Users/dsemiraz/projects/alpaca/src/alpaca.txt").toPath())
                .forEach(System.out::println);
    }
}
