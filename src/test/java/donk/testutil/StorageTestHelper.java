package donk.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class StorageTestHelper {
    private final Path dataPath = Paths.get("data", "donk.txt");
    private final Path tempDir;
    private Path backupPath;

    public StorageTestHelper(Path tempDir) {
        this.tempDir = tempDir;
    }

    public void backup() throws IOException {
        if (Files.exists(dataPath)) {
            backupPath = tempDir.resolve("donk.txt");
            Files.createDirectories(tempDir);
            Files.move(dataPath, backupPath, REPLACE_EXISTING);
        }
    }

    public void restore() throws IOException {
        Files.deleteIfExists(dataPath);
        if (backupPath != null && Files.exists(backupPath)) {
            if (dataPath.getParent() != null) {
                Files.createDirectories(dataPath.getParent());
            }
            Files.move(backupPath, dataPath, REPLACE_EXISTING);
        }
    }

    public Path getDataPath() {
        return dataPath;
    }
}
