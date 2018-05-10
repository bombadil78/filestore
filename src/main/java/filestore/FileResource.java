package filestore;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/files")
public class FileResource {

    private final FileService fileService;

    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public List<File> getAll() {
        return fileService.findAll();
    }

    @PostMapping
    public File addFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.store(file.getBytes());
    }
}