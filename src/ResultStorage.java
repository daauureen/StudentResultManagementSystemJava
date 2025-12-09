import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ResultStorage {
    private final Path file;

    public ResultStorage(String filename) {
        this.file = Paths.get(filename);
    }

    public synchronized void save(Result r) throws IOException {
        Files.createDirectories(file.getParent() == null ? Paths.get(".") : file.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(r.toStorageLine());
            writer.newLine();
        }
    }

    public synchronized List<Result> loadAll() throws IOException {
        List<Result> out = new ArrayList<>();
        if (!Files.exists(file)) return out;
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Result r = Result.fromStorageLine(line);
                if (r != null) out.add(r);
            }
        }
        return out;
    }
}
