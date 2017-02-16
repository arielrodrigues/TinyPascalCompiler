package utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    public boolean writeToFile(String filename, List<String> lines) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        return true;
    }

    public boolean writeToFile(String filename, String text) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writer.write(text);
        }
        return true;
    }

}
