package com.bilolbek.myResume.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.bilolbek.myResume.service.ContactService;
import com.bilolbek.myResume.service.ProjectsService;

@Controller
public class IndexController {

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<String> getAllProjects(){
        return ResponseEntity.ok(projectsService.getAllProjects());
    }

    @GetMapping("/getAllTechnologies")
    public ResponseEntity<String> getAllTechnologies(@RequestParam("projectId") Long projectId){
        

        return ResponseEntity.ok(projectsService.getAllTechnologies(projectId));
    }

    @GetMapping ("/getContact")
    public ResponseEntity<String> getContact(){
        return ResponseEntity.ok(contactService.getContact());
    }
}
