package filestore;

import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

public enum FileType {
    TEXT(MimeTypeUtils.TEXT_PLAIN, "txt"),
    JPG(MimeTypeUtils.IMAGE_JPEG, "jpg"),
    PNG(MimeTypeUtils.IMAGE_PNG, "png"),
    APPLICATION(MimeTypeUtils.APPLICATION_OCTET_STREAM, "exe");

    FileType(MimeType mimeType, String fileExtension) {
        this.mimeType = mimeType;
        this.fileExtension = fileExtension;
    }

    private MimeType mimeType;
    private String fileExtension;

    public MimeType getMimeType() {
        return mimeType;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
