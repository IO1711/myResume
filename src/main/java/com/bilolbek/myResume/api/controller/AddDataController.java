package com.bilolbek.myResume.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bilolbek.myResume.api.DTO.ProjectsDTO;
import com.bilolbek.myResume.api.model.Contact;
import com.bilolbek.myResume.api.model.Hobbies;
import com.bilolbek.myResume.api.model.MyData;
import com.bilolbek.myResume.service.ContactService;
import com.bilolbek.myResume.service.HobbiesService;
import com.bilolbek.myResume.service.MyDataService;
import com.bilolbek.myResume.service.ProjectsService;
import com.bilolbek.myResume.api.model.Projects;


import java.util.List;



@Controller
public class AddDataController {

    @Autowired
    private MyDataService myDataService;

    @Autowired
    private HobbiesService hobbiesService;

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private ContactService contactService;


    @GetMapping("/addData")
    public String addDataPage(){
        return "addData";
    }

    @GetMapping("/getMyData")
    public ResponseEntity<String> getMyData(){
        return ResponseEntity.ok(myDataService.getMyData());
    }

    @GetMapping("/getMyAge")
    public ResponseEntity<Integer> getMyAge(){
        int birthYear = 1999;

        LocalDate currentDate = LocalDate.now();

        LocalDate birthDate = LocalDate.of(birthYear, 11, 17);

        int age = Period.between(birthDate, currentDate).getYears();

        return ResponseEntity.ok(age);
    }

    @PostMapping("/saveMyData")
    public ResponseEntity<String> saveMyData(MyData myData){
        return ResponseEntity.ok(myDataService.saveMyData(myData));
    }

    @PutMapping("/updateMyData")
    public ResponseEntity<String> updateMyData(MyData myData){
        return ResponseEntity.ok(myDataService.updateMyData(myData));
    }

    @GetMapping("/getHobbies")
    public ResponseEntity<String> getHobbies(){
        return ResponseEntity.ok(hobbiesService.getAllHobbies());
    }

    @PostMapping("/setHobbies")
    public ResponseEntity<String> setHobbies(Hobbies hobbies){
        return ResponseEntity.ok(hobbiesService.setHobbies(hobbies));
    }

    @PostMapping("/saveProject")
    public ResponseEntity<String> saveProject(
            @RequestParam("projectName") String projectName,
            @RequestParam("projectDescription") String projectDescription,
            @RequestParam("file") MultipartFile file,
            @RequestParam("technology") List<String> technology) throws Exception{


        Projects project = projectsService.saveProject(projectName, projectDescription, file, technology);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(project.getId()))
                .toUriString();
        
        return ResponseEntity.ok("File uploaded successfully, download here: "+downloadUrl);
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException{
        Projects project = projectsService.getProject(id);
        Path filePath = Paths.get(project.getFilePath());

        byte[] fileContent = Files.readAllBytes(filePath);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + project.getFileName() + "\"")
                .body(fileContent);
    }

    @GetMapping("/getProject/{id}")
    public ResponseEntity<ProjectsDTO> getProject(@PathVariable Long id){
        Projects project = projectsService.getProject(id);
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/assets/")
                            .path(project.getFileName())
                            .toUriString();
        
        ProjectsDTO projectsDTO = new ProjectsDTO(project.getId(), project.getProjectName(), project.getProjectDescription(), project.getFileName(), downloadUrl, project.getFileType());

        return ResponseEntity.ok(projectsDTO);
    }


    @PostMapping("/saveContact")
    public ResponseEntity<String> saveContact(Contact contact){
        return ResponseEntity.ok(contactService.saveContact(contact));
    }
}
