package filestore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FileService {

    private static final Logger LOG = LogManager.getLogger(FileService.class);

    @Value("${application.filestore.directory}")
    private String fileStoreDirectory;

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File store(byte[] content) {
        // check content not empty
        // check file size limit
        // check mime type, possibly using probe method

        File file = new File();
        String fileName = generateFileName();
        file.setFileName(fileName);
        file.setFileType(FileType.TEXT);

        try {
            Path saveFile = Paths.get(fileStoreDirectory, file.getFileName());
            Files.write(saveFile, file.getContent());
        } catch (IOException ex) {
            LOG.error("Unable to store file", ex);
        }

        return fileRepository.save(file);
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public File load(String fileName) {
        File someFile = fileRepository.findByFileName(fileName);
        if (someFile == null) {
            throw new NoSuchElementException(String.format("File with name %s not found", fileName));
        }

        Path somePath = Paths.get(fileStoreDirectory, fileName);
        if (Files.exists(somePath)) {
            throw new RuntimeException(String.format("File is in database, but cannot be found: %s", fileName));
        }

        try {
            byte[] content = Files.readAllBytes(somePath);
            someFile.setContent(content);
        } catch (IOException ex) {
            LOG.error(String.format("Unable to read file: %s", fileName), ex);
        }

        return someFile;
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}