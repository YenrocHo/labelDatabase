package com.fc.test.model.auto;

import java.io.Serializable;

public class TSysLearnFile implements Serializable {
    private String fileId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String fileUrl;
    /**
     * 上传时间
     */
    private String uploadTime;

    private static final long serialVersionUID = 1L;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

    public TSysLearnFile() {
    }
    public TSysLearnFile(String fileId, String fileName, String fileUrl, String uploadTime) {
        super();
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadTime = uploadTime;
    }

    public TSysLearnFile(String fileName, String fileUrl, String uploadTime) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadTime = uploadTime;
    }
}