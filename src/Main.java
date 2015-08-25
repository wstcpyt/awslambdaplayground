import com.infomagnetic.inverseproblem.CEA.Individual;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yutongpang on 8/24/15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/hello.txt");
        byte[] data = Files.readAllBytes(path);
        Individual[][] population = SerializationUtils.deserialize(data);
    }
}
