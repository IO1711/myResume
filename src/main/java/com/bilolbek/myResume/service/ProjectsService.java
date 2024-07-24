package com.bilolbek.myResume.service;



import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bilolbek.myResume.repositories.ProjectTechnologiesRepository;
import com.bilolbek.myResume.repositories.ProjectsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bilolbek.myResume.api.DTO.ProjectsDTO;
import com.bilolbek.myResume.api.model.ProjectTechnologies;
import com.bilolbek.myResume.api.model.Projects;

import jakarta.transaction.Transactional;


@Service

public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private ProjectTechnologiesRepository projectTechnologiesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //private URL saveFolder = getClass().getClassLoader().getResource("static/assets/");

    private final String UPLOAD_DIR = "src/main/resources/static/assets/";//saveFolder.getPath();

    

    

    @Transactional
    public Projects saveProject(String projectName, String projectDescription, String projectLink, MultipartFile file, List<String> technology) throws Exception{
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR);

        
        try {
            if(fileName.contains("..")){
                throw new Exception("Filename contains invalid path sequence: "+fileName);
            }

            if(Files.notExists(filePath)){
                Files.createDirectories(filePath);
                
            }

            Path filePathDir = filePath.resolve(fileName);
            
            Files.copy(file.getInputStream(), filePathDir);
            
            

            Projects project = new Projects(projectName, projectDescription, projectLink, fileName, file.getContentType(), filePathDir.toString());
            projectsRepository.save(project);
            
            
            for(String technologyMember : technology){
                ProjectTechnologies projectTechnologies = new ProjectTechnologies();
                projectTechnologies.setTechnology(technologyMember);
                projectTechnologies.setProject(project);
                projectTechnologiesRepository.save(projectTechnologies);
            
            }

            return project;
        } catch (Exception e) {
            
            throw new Exception("Could not save file: "+fileName);
        }

        
    }

    @Transactional
    public Projects getProject(Long id){
        return projectsRepository.findById(id).orElse(null);
    }

    @Transactional
    public String getAllProjects(){
        Iterable<Projects> allProjectsIterable = projectsRepository.findAll();

        List<Projects> allProjectsProduct = new ArrayList<>();

        allProjectsProduct = StreamSupport.stream(allProjectsIterable.spliterator(), false).collect(Collectors.toList());

        List<ProjectsDTO> allProjects = new ArrayList<>();

        for(Projects project : allProjectsProduct){
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/assets/")
                            .path(project.getFileName())
                            .toUriString();

            ProjectsDTO projectsDTO = new ProjectsDTO(project.getId(), project.getProjectName(), project.getProjectDescription(), project.getProjectLink(), project.getFileName(), downloadUrl, project.getFileType());

            allProjects.add(projectsDTO);
        }

        try{
            String projectsAsJson = objectMapper.writeValueAsString(allProjects);
            return projectsAsJson;
        }
        catch(JsonProcessingException e){
            return "could not make json with all projects";
        }
    }

    @Transactional
    public String getAllTechnologies(Long projectId){

        
        Iterable<ProjectTechnologies> allTechnologies = projectTechnologiesRepository.findByProjectId(projectId);

        try {
            String techAsJson = objectMapper.writeValueAsString(allTechnologies);
            
            return techAsJson;
        } catch (JsonProcessingException e) {
            return "error in json processing in getAllTechnologies for project with id: "+projectId;
        }
    }
}
