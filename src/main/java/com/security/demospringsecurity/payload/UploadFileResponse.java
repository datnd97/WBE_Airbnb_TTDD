package com.security.demospringsecurity.payload;

public class UploadFileResponse {
    private String fileName;
    private String fileDowloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse() {
    }

    public UploadFileResponse(String fileName, String fileDowloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDowloadUri = fileDowloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDowloadUri() {
        return fileDowloadUri;
    }

    public void setFileDowloadUri(String fileDowloadUri) {
        this.fileDowloadUri = fileDowloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
