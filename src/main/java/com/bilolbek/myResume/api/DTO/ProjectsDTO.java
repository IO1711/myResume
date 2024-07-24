package com.bilolbek.myResume.api.DTO;



public class ProjectsDTO {

    private Long id;

    private String projectName;
    private String projectDescription;
    private String projectLink;

    private String fileName;

    private String fileType;

    private String downloadUrl;

    

    public ProjectsDTO(Long id, String projectName, String projectDescription, String projectLink, String fileName, String downloadUrl, String fileType){
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLink = projectLink;
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
        this.fileType = fileType;
        
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectLink() {
        return this.projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    



    

}
