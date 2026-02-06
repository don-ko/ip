package donk.testutil;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility for backing up and restoring the `data/donk.txt` file during tests.
 */
public class StorageTestHelper {
    private final Path dataPath = Paths.get("data", "donk.txt");
    private final Path tempDir;
    private Path backupPath;

    /**
     * Creates a helper that stores backups in the given temporary directory.
     *
     * @param tempDir the directory to store backup files */
    public StorageTestHelper(Path tempDir) {
        this.tempDir = tempDir;
    }

    /**
     * Backs up the data file to the temporary directory if it exists.
     *
     * @throws IOException if the backup operation fails */
    public void backup() throws IOException {
        if (Files.exists(dataPath)) {
            backupPath = tempDir.resolve("donk.txt");
            Files.createDirectories(tempDir);
            Files.move(dataPath, backupPath, REPLACE_EXISTING);
        }
    }

    /**
     * Restores the data file from the backup if it exists.
     *
     * @throws IOException if the restore operation fails */
    public void restore() throws IOException {
        Files.deleteIfExists(dataPath);
        if (backupPath != null && Files.exists(backupPath)) {
            if (dataPath.getParent() != null) {
                Files.createDirectories(dataPath.getParent());
            }
            Files.move(backupPath, dataPath, REPLACE_EXISTING);
        }
    }

    /**
     * Returns the path to the data file.
     *
     * @return the data file path */
    public Path getDataPath() {
        return dataPath;
    }
}
