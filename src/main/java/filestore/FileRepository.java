package filestore;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface FileRepository extends Repository<File, Long> {

    File findByFileName(String fileName);
    List<File> findAll();
    File save(File file);

}
