package filestore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String fileName;

    @NotNull
    @Enumerated
    private FileType fileType;

    @Transient
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}